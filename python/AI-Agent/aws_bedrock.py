import os
from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from strands import Agent
from strands.models.bedrock import BedrockModel

load_dotenv()

AWS_REGION = os.getenv("AWS_REGION", "us-east-1")
BEDROCK_MODEL_ID = os.getenv("BEDROCK_MODEL_ID", "anthropic.claude-sonnet-4-20250514-v1:0")

app = FastAPI(title="SRE Incident Assistant", version="1.0.0")


# -----------------------------------
# Request / Response models
# -----------------------------------
class IncidentRequest(BaseModel):
    service_name: str
    alert_message: str


class IncidentResponse(BaseModel):
    summary: str
    service_context: dict


# -----------------------------------
# Production-style service registry
# This is where the app knows where to look
# -----------------------------------
SERVICE_REGISTRY = {
    "checkout-service": {
        "environment": "production",
        "region": "eu-west-2",
        "cluster": "eks-prod-1",
        "namespace": "payments",
        "logs_source": "grafana-loki",
        "metrics_source": "datadog",
        "deployment_source": "argocd",
        "runbook_url": "https://internal-wiki/runbooks/checkout-service",
        "dashboard_url": "https://grafana.example.com/d/checkout",
        "datadog_service_name": "checkout-service",
    },
    "user-service": {
        "environment": "production",
        "region": "eu-west-1",
        "cluster": "eks-prod-2",
        "namespace": "identity",
        "logs_source": "cloudwatch",
        "metrics_source": "cloudwatch",
        "deployment_source": "github-actions",
        "runbook_url": "https://internal-wiki/runbooks/user-service",
        "dashboard_url": "https://cloudwatch.aws.amazon.com/",
        "datadog_service_name": "user-service",
    },
}


# -----------------------------------
# Helper: service context lookup
# -----------------------------------
def get_service_context(service_name: str) -> dict:
    config = SERVICE_REGISTRY.get(service_name)
    if not config:
        raise ValueError(f"Unknown service: {service_name}")
    return config


# -----------------------------------
# Tools
# In production, these would call real APIs
# -----------------------------------
def get_service_details(service_name: str) -> str:
    """
    Returns where the service runs and what monitoring systems it uses.
    """
    cfg = get_service_context(service_name)
    return (
        f"Service {service_name} runs in cluster {cfg['cluster']} "
        f"namespace {cfg['namespace']} region {cfg['region']}. "
        f"Logs source: {cfg['logs_source']}. "
        f"Metrics source: {cfg['metrics_source']}. "
        f"Deployment source: {cfg['deployment_source']}."
    )


def get_recent_logs(service_name: str) -> str:
    """
    Example log lookup tool.
    Replace with actual Grafana Loki / CloudWatch / Splunk query.
    """
    cfg = get_service_context(service_name)

    if cfg["logs_source"] == "grafana-loki":
        return (
            f"Checked Grafana Loki for {service_name} in cluster {cfg['cluster']} "
            f"namespace {cfg['namespace']}. "
            f"Found repeated 502 errors from /api/checkout and upstream timeout messages."
        )

    if cfg["logs_source"] == "cloudwatch":
        return (
            f"Checked CloudWatch logs for {service_name} in region {cfg['region']}. "
            f"Found increased application exceptions and timeout errors."
        )

    return f"No log source configured for {service_name}."


def get_recent_metrics(service_name: str) -> str:
    """
    Example metrics lookup tool.
    Replace with actual Datadog / CloudWatch metrics query.
    """
    cfg = get_service_context(service_name)

    if cfg["metrics_source"] == "datadog":
        return (
            f"Checked Datadog metrics for {service_name}. "
            f"CPU increased from 35% to 92%, p95 latency rose to 2.8 seconds, "
            f"and HTTP 5xx increased sharply in the last 15 minutes."
        )

    if cfg["metrics_source"] == "cloudwatch":
        return (
            f"Checked CloudWatch metrics for {service_name}. "
            f"Error count and response time increased in the last 15 minutes."
        )

    return f"No metrics source configured for {service_name}."


def get_recent_deployments(service_name: str) -> str:
    """
    Example deployment lookup tool.
    Replace with ArgoCD / GitHub Actions / Jenkins / Spinnaker lookup.
    """
    cfg = get_service_context(service_name)

    if cfg["deployment_source"] == "argocd":
        return (
            f"Checked ArgoCD for {service_name}. "
            f"A new rollout completed 12 minutes ago with image tag checkout:v2026.03.24.1."
        )

    if cfg["deployment_source"] == "github-actions":
        return (
            f"Checked GitHub Actions for {service_name}. "
            f"A deployment workflow finished successfully 20 minutes ago."
        )

    return f"No deployment source configured for {service_name}."


def get_runbook(service_name: str) -> str:
    """
    Example runbook lookup tool.
    """
    cfg = get_service_context(service_name)
    return (
        f"Runbook for {service_name}: {cfg['runbook_url']}. "
        f"Standard checks: verify rollout, inspect pod restarts, check downstream DB latency, "
        f"confirm ingress health, and assess whether rollback is needed."
    )


# -----------------------------------
# Strands + Bedrock
# -----------------------------------
model = BedrockModel(
    model_id=BEDROCK_MODEL_ID,
    region_name=AWS_REGION,
)

agent = Agent(
    model=model,
    tools=[
        get_service_details,
        get_recent_logs,
        get_recent_metrics,
        get_recent_deployments,
        get_runbook,
    ],
    system_prompt="""
You are a production SRE incident assistant.

Always use the available tools before answering.

Your task:
1. identify where the service runs
2. inspect logs, metrics, deployments, and runbook guidance
3. summarize the likely root cause
4. provide supporting evidence
5. recommend the next action
6. assign a severity

Keep the answer concise and operationally useful.
""",
)


@app.get("/ping")
def ping():
    return {"status": "ok"}


@app.post("/investigate", response_model=IncidentResponse)
def investigate(req: IncidentRequest):
    try:
        service_context = get_service_context(req.service_name)

        prompt = f"""
Investigate this production alert.

Service name: {req.service_name}
Incoming alert: {req.alert_message}

Use the tools to determine:
- where this service runs
- what the logs show
- what the metrics show
- whether a recent deployment may be related
- what the runbook suggests

Return:
1. probable cause
2. evidence
3. recommended next step
4. severity
"""

        result = agent(prompt)

        return IncidentResponse(
            summary=str(result),
            service_context=service_context,
        )

    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
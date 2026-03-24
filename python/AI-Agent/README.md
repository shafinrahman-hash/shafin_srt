# Strands AI Agent

A simple AI agent built with the [Strands Agents](https://github.com/strands-agents/sdk-python) SDK.

## Setup

```bash
python -m venv .venv
source .venv/bin/activate  # Windows: .venv\Scripts\activate

pip install -r requirements.txt
```

## Model Provider

**Default**: Uses Amazon Bedrock (Claude) — requires AWS credentials and model access in us-west-2.

**OpenAI**: Set `OPENAI_API_KEY` and use:
```python
from strands import Agent
from strands.models import OpenAIModel

model = OpenAIModel(model_id="gpt-4o-mini")
agent = Agent(model=model, tools=[...])
```

**Ollama** (local): `pip install strands-agents[ollama]` and:
```python
from strands.models.ollama import OllamaModel

model = OllamaModel(host="http://localhost:11434", model_id="llama3")
agent = Agent(model=model, tools=[...])
```

## Run

```bash
python agent.py
```

## Custom Tools

Add tools with the `@tool` decorator — the docstring is used by the LLM to understand when to use it:

```python
from strands import tool

@tool
def my_tool(query: str) -> str:
    """Description for the LLM."""
    return result
```

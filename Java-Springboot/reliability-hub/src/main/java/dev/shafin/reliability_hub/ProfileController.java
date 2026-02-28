package dev.shafin.reliability_hub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping(value = "/profile", produces = "text/html")
    public String profile() {
        return """
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Profile – Reliability Hub</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
  <style>
    *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
    :root {
      --bg: #0f1117;
      --surface: #1a1d27;
      --surface2: #22263a;
      --border: #2e3250;
      --accent: #6366f1;
      --accent-hover: #4f52d6;
      --text: #e2e4f0;
      --muted: #8b8fa8;
      --tag-bg: #1e2240;
      --tag-text: #a5b4fc;
    }
    body { font-family: 'Inter', sans-serif; background: var(--bg); color: var(--text); min-height: 100vh; display: flex; flex-direction: column; }

    /* NAV */
    nav {
      display: flex; align-items: center; justify-content: space-between;
      padding: 0 32px; height: 60px;
      background: var(--surface); border-bottom: 1px solid var(--border);
      position: sticky; top: 0; z-index: 100;
    }
    .nav-brand { display: flex; align-items: center; gap: 10px; text-decoration: none; }
    .nav-brand-icon {
      width: 30px; height: 30px; background: var(--accent); border-radius: 8px;
      display: flex; align-items: center; justify-content: center; font-size: 0.85rem;
    }
    .nav-brand-text { font-weight: 700; font-size: 0.95rem; color: var(--text); letter-spacing: -0.3px; }
    .nav-brand-text span { color: var(--muted); font-weight: 400; }
    .nav-links { display: flex; gap: 4px; }
    .nav-links a {
      padding: 6px 14px; border-radius: 6px; font-size: 0.875rem; font-weight: 500;
      color: var(--muted); text-decoration: none; transition: color .15s, background .15s;
    }
    .nav-links a:hover { color: var(--text); background: var(--surface2); }
    .nav-links a.active { color: var(--text); background: var(--surface2); }

    /* LAYOUT */
    .page { max-width: 900px; margin: 0 auto; padding: 52px 28px 80px; flex: 1; }

    /* PROFILE HERO */
    .profile-hero {
      display: flex; align-items: flex-start; gap: 28px;
      background: var(--surface); border: 1px solid var(--border); border-radius: 16px;
      padding: 32px; margin-bottom: 36px;
    }
    .avatar {
      width: 72px; height: 72px; border-radius: 50%; flex-shrink: 0;
      background: linear-gradient(135deg, var(--accent) 0%, #818cf8 100%);
      display: flex; align-items: center; justify-content: center;
      font-size: 1.6rem; font-weight: 700; color: #fff; letter-spacing: -1px;
    }
    .profile-meta { flex: 1; min-width: 0; }
    .profile-name { font-size: 1.4rem; font-weight: 700; color: #fff; letter-spacing: -0.5px; margin-bottom: 4px; }
    .profile-title { font-size: 0.875rem; color: var(--muted); margin-bottom: 14px; }
    .profile-bio { font-size: 0.88rem; color: var(--text); line-height: 1.7; margin-bottom: 20px; }
    .profile-actions { display: flex; gap: 10px; flex-wrap: wrap; }
    .btn-primary {
      display: inline-flex; align-items: center; gap: 6px;
      padding: 8px 18px; background: var(--accent); color: #fff;
      border-radius: 7px; font-size: 0.85rem; font-weight: 600;
      text-decoration: none; transition: background .15s, transform .1s;
    }
    .btn-primary:hover { background: var(--accent-hover); transform: translateY(-1px); }
    .btn-secondary {
      display: inline-flex; align-items: center; gap: 6px;
      padding: 8px 18px; background: transparent; color: var(--text);
      border: 1px solid var(--border); border-radius: 7px; font-size: 0.85rem; font-weight: 600;
      text-decoration: none; transition: border-color .15s, background .15s, transform .1s;
    }
    .btn-secondary:hover { border-color: var(--accent); background: var(--surface2); transform: translateY(-1px); }

    /* META CHIPS */
    .meta-chips { display: flex; gap: 8px; flex-wrap: wrap; margin-top: 14px; }
    .meta-chip {
      display: inline-flex; align-items: center; gap: 5px;
      background: var(--surface2); border: 1px solid var(--border); border-radius: 999px;
      padding: 4px 12px; font-size: 0.75rem; color: var(--muted);
    }

    /* SECTION */
    .section { margin-bottom: 36px; }
    .section-title {
      font-size: 0.7rem; font-weight: 700; letter-spacing: 1.4px;
      text-transform: uppercase; color: var(--muted);
      display: flex; align-items: center; gap: 10px; margin-bottom: 16px;
    }
    .section-title::after { content: ''; flex: 1; height: 1px; background: var(--border); }

    /* SKILL GRID */
    .skill-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(265px, 1fr)); gap: 12px; }
    .skill-card {
      background: var(--surface); border: 1px solid var(--border); border-radius: 12px;
      padding: 18px 20px; transition: border-color .2s, transform .15s;
    }
    .skill-card:hover { border-color: var(--accent); transform: translateY(-2px); }
    .skill-card .skill-title { font-size: 0.875rem; font-weight: 600; color: var(--text); margin-bottom: 6px; }
    .skill-card .skill-desc { font-size: 0.8rem; color: var(--muted); line-height: 1.6; margin-bottom: 12px; }
    .tag-cloud { display: flex; flex-wrap: wrap; gap: 6px; }
    .tag {
      background: var(--tag-bg); color: var(--tag-text);
      border: 1px solid #2a2f56; border-radius: 4px;
      padding: 2px 9px; font-size: 0.72rem; font-weight: 500;
    }

    /* FOOTER */
    footer {
      text-align: center; padding: 22px 24px; border-top: 1px solid var(--border);
      font-size: 0.78rem; color: var(--muted);
    }
    footer a { color: var(--muted); text-decoration: none; }
    footer a:hover { color: var(--text); }
  </style>
</head>
<body>

  <nav>
    <a class="nav-brand" href="/">
      <div class="nav-brand-icon">&#9678;</div>
      <span class="nav-brand-text">reliability<span>-hub</span></span>
    </a>
    <div class="nav-links">
      <a href="/">Dashboard</a>
      <a href="/profile" class="active">Profile</a>
      <a href="/links">Quick Links</a>
    </div>
  </nav>

  <div class="page">

    <!-- PROFILE HERO -->
    <div class="profile-hero">
      <div class="avatar">MS</div>
      <div class="profile-meta">
        <div class="profile-name">Mohammed Shafin Thayyil</div>
        <div class="profile-title">Technical Architect &ndash; Site Reliability Engineer</div>
        <div class="profile-bio">
          I build reliable, secure, and scalable platforms using AWS, Kubernetes, Terraform, and
          strong observability practices. Focused on reducing toil, improving system reliability,
          and enabling engineering teams to ship with confidence.
        </div>
        <div class="profile-actions">
          <a class="btn-primary" href="https://www.linkedin.com/in/shafint/" target="_blank">
            &#x1F517;&nbsp; LinkedIn
          </a>
          <a class="btn-secondary" href="/">
            &#9678;&nbsp; Reliability Hub
          </a>
        </div>
        <div class="meta-chips">
          <span class="meta-chip">&#x1F4CD; London, UK</span>
          <span class="meta-chip">&#x2699;&#xFE0F; SRE / Platform Engineering</span>
          <span class="meta-chip">&#x2601;&#xFE0F; AWS &bull; Kubernetes</span>
        </div>
      </div>
    </div>

    <!-- EXPERTISE -->
    <div class="section">
      <div class="section-title">Expertise</div>
      <div class="skill-grid">

        <div class="skill-card">
          <div class="skill-title">SRE / DevOps</div>
          <div class="skill-desc">SLIs/SLOs, incident response, on-call, automation, and reliability improvements</div>
          <div class="tag-cloud">
            <span class="tag">SLOs</span><span class="tag">Incident Response</span><span class="tag">Automation</span>
          </div>
        </div>

        <div class="skill-card">
          <div class="skill-title">Cloud &amp; Kubernetes</div>
          <div class="skill-desc">AWS (EC2/EKS/ECS/Lambda/RDS/S3/VPC/CloudFront/Route 53), Docker, Helm — production hardening &amp; scalability</div>
          <div class="tag-cloud">
            <span class="tag">AWS</span><span class="tag">EKS</span><span class="tag">Docker</span><span class="tag">Helm</span>
          </div>
        </div>

        <div class="skill-card">
          <div class="skill-title">Observability</div>
          <div class="skill-desc">Datadog; Grafana Stack (Grafana/Prometheus/Loki/Tempo); Alloy/Promtail; OpenTelemetry</div>
          <div class="tag-cloud">
            <span class="tag">Grafana</span><span class="tag">Prometheus</span><span class="tag">OTel</span><span class="tag">Loki</span>
          </div>
        </div>

        <div class="skill-card">
          <div class="skill-title">Platform Tooling</div>
          <div class="skill-desc">Terraform, CloudFormation, Ansible, Puppet; CI/CD &amp; GitOps with Jenkins, GitLab CI, Argo CD</div>
          <div class="tag-cloud">
            <span class="tag">Terraform</span><span class="tag">Argo CD</span><span class="tag">GitOps</span><span class="tag">Jenkins</span>
          </div>
        </div>

        <div class="skill-card">
          <div class="skill-title">Security</div>
          <div class="skill-desc">IAM, KMS, Secrets Manager, WAF, Security Groups/NACLs, TLS/PKI (ACM, cert rotation); Nessus, AWS Inspector</div>
          <div class="tag-cloud">
            <span class="tag">IAM</span><span class="tag">KMS</span><span class="tag">WAF</span><span class="tag">TLS</span>
          </div>
        </div>

        <div class="skill-card">
          <div class="skill-title">Engineering</div>
          <div class="skill-desc">Python, Java, Spring Boot, Gradle, Kotlin; MySQL, PostgreSQL, Cassandra, InfluxDB</div>
          <div class="tag-cloud">
            <span class="tag">Java</span><span class="tag">Python</span><span class="tag">Spring Boot</span><span class="tag">Kotlin</span>
          </div>
        </div>

      </div>
    </div>

  </div>

  <footer>
    <a href="/">Reliability Hub</a> &nbsp;&bull;&nbsp;
    <a href="/links">All endpoints</a>
  </footer>

</body>
</html>
                """;
    }
}

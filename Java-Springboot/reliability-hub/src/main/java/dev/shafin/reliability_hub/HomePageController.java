package dev.shafin.reliability_hub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return """
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Reliability Hub</title>
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
      --green: #34d399;
      --green-bg: #0d2e20;
      --green-border: #1a4d35;
      --amber: #fbbf24;
      --amber-bg: #2d2008;
      --red: #f87171;
      --red-bg: #2d0c0c;
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

    /* PAGE HEADER */
    .page-header { max-width: 1000px; margin: 48px auto 36px; padding: 0 28px; }
    .page-header h1 { font-size: 1.6rem; font-weight: 700; letter-spacing: -0.6px; color: #fff; margin-bottom: 6px; }
    .page-header p { font-size: 0.875rem; color: var(--muted); }

    /* STAT STRIP */
    .stat-strip {
      max-width: 1000px; margin: 0 auto 40px; padding: 0 28px;
      display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 14px;
    }
    .stat-card {
      background: var(--surface); border: 1px solid var(--border); border-radius: 12px;
      padding: 18px 20px; display: flex; flex-direction: column; gap: 6px;
    }
    .stat-card .stat-label { font-size: 0.72rem; font-weight: 600; letter-spacing: 0.8px; text-transform: uppercase; color: var(--muted); }
    .stat-card .stat-value { font-size: 1.5rem; font-weight: 700; color: #fff; letter-spacing: -0.5px; }
    .stat-card .stat-sub { font-size: 0.75rem; color: var(--muted); }
    .stat-card .stat-dot { width: 8px; height: 8px; border-radius: 50%; display: inline-block; margin-right: 5px; }
    .dot-green { background: var(--green); box-shadow: 0 0 6px var(--green); }
    .dot-amber { background: var(--amber); }
    .dot-red   { background: var(--red); }

    /* SECTION */
    .content { max-width: 1000px; margin: 0 auto; padding: 0 28px; flex: 1; }
    .section { margin-bottom: 44px; }
    .section-header {
      display: flex; align-items: center; justify-content: space-between;
      margin-bottom: 16px;
    }
    .section-title {
      font-size: 0.7rem; font-weight: 700; letter-spacing: 1.4px;
      text-transform: uppercase; color: var(--muted);
      display: flex; align-items: center; gap: 10px;
    }
    .section-title::after { content: ''; display: inline-block; width: 120px; height: 1px; background: var(--border); }
    .section-action { font-size: 0.8rem; color: var(--accent); text-decoration: none; }
    .section-action:hover { text-decoration: underline; }

    /* ENDPOINT GRID */
    .endpoint-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px; }
    .endpoint-card {
      background: var(--surface); border: 1px solid var(--border); border-radius: 10px;
      padding: 16px 18px; text-decoration: none;
      display: flex; align-items: center; justify-content: space-between; gap: 12px;
      transition: border-color .2s, background .15s, transform .15s;
    }
    .endpoint-card:hover { border-color: var(--accent); background: var(--surface2); transform: translateY(-2px); }
    .endpoint-card-left { display: flex; flex-direction: column; gap: 3px; min-width: 0; }
    .endpoint-path { font-size: 0.85rem; font-weight: 600; color: var(--text); white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
    .endpoint-desc { font-size: 0.76rem; color: var(--muted); }
    .badge {
      font-size: 0.65rem; font-weight: 700; letter-spacing: 0.5px;
      padding: 3px 8px; border-radius: 4px; white-space: nowrap; flex-shrink: 0;
    }
    .badge-get  { background: var(--green-bg); color: var(--green); border: 1px solid var(--green-border); }
    .badge-mgmt { background: var(--amber-bg); color: var(--amber); border: 1px solid #4d3810; }

    /* FOOTER */
    footer {
      margin-top: 64px; text-align: center; padding: 22px 24px;
      border-top: 1px solid var(--border); font-size: 0.78rem; color: var(--muted);
    }
    footer a { color: var(--muted); text-decoration: none; }
    footer a:hover { color: var(--text); }
    footer code { background: var(--surface2); padding: 2px 6px; border-radius: 4px; }
  </style>
</head>
<body>

  <nav>
    <a class="nav-brand" href="/">
      <div class="nav-brand-icon">&#9678;</div>
      <span class="nav-brand-text">reliability<span>-hub</span></span>
    </a>
    <div class="nav-links">
      <a href="/" class="active">Dashboard</a>
      <a href="/profile">Profile</a>
      <a href="/links">Quick Links</a>
    </div>
  </nav>

  <div class="page-header">
    <h1>Reliability Hub</h1>
    <p>SRE observability dashboard &mdash; Spring Boot &bull; Actuator &bull; Prometheus</p>
  </div>

  <!-- STAT STRIP -->
  <div class="stat-strip">
    <div class="stat-card">
      <span class="stat-label">Application</span>
      <span class="stat-value" style="font-size:1.1rem; margin-top:2px;">reliability-hub</span>
      <span class="stat-sub">v0.0.1-SNAPSHOT</span>
    </div>
    <div class="stat-card">
      <span class="stat-label">Health Status</span>
      <span class="stat-value" style="font-size:1rem; color: var(--green); margin-top:4px;">
        <span class="stat-dot dot-green"></span>UP
      </span>
      <span class="stat-sub"><a href="/actuator/health" style="color:var(--muted); text-decoration:none;">/actuator/health</a></span>
    </div>
    <div class="stat-card">
      <span class="stat-label">Runtime</span>
      <span class="stat-value" style="font-size:1rem; margin-top:4px;">JVM 17</span>
      <span class="stat-sub"><a href="/api/sre/runtime" style="color:var(--muted); text-decoration:none;">View runtime stats</a></span>
    </div>
    <div class="stat-card">
      <span class="stat-label">Metrics</span>
      <span class="stat-value" style="font-size:1rem; margin-top:4px;">Prometheus</span>
      <span class="stat-sub"><a href="/actuator/prometheus" style="color:var(--muted); text-decoration:none;">/actuator/prometheus</a></span>
    </div>
  </div>

  <div class="content">

    <!-- SRE API -->
    <div class="section">
      <div class="section-header">
        <span class="section-title">SRE API</span>
        <a class="section-action" href="/links">View all &rarr;</a>
      </div>
      <div class="endpoint-grid">
        <a class="endpoint-card" href="/api/sre/ping">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/api/sre/ping</span>
            <span class="endpoint-desc">Status check &amp; server time</span>
          </div>
          <span class="badge badge-get">GET</span>
        </a>
        <a class="endpoint-card" href="/api/sre/runtime">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/api/sre/runtime</span>
            <span class="endpoint-desc">JVM uptime, heap usage, processors</span>
          </div>
          <span class="badge badge-get">GET</span>
        </a>
      </div>
    </div>

    <!-- ACTUATOR -->
    <div class="section">
      <div class="section-header">
        <span class="section-title">Actuator Endpoints</span>
        <a class="section-action" href="/links">View all &rarr;</a>
      </div>
      <div class="endpoint-grid">
        <a class="endpoint-card" href="/actuator/health">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/health</span>
            <span class="endpoint-desc">Liveness &amp; readiness probes</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/metrics">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/metrics</span>
            <span class="endpoint-desc">Micrometer metrics index</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/prometheus">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/prometheus</span>
            <span class="endpoint-desc">Prometheus scrape endpoint</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/info">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/info</span>
            <span class="endpoint-desc">Application info &amp; metadata</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/loggers">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/loggers</span>
            <span class="endpoint-desc">Logger levels — read &amp; update</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/env">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/env</span>
            <span class="endpoint-desc">Environment properties &amp; config</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/threaddump">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/threaddump</span>
            <span class="endpoint-desc">JVM thread dump</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
        <a class="endpoint-card" href="/actuator/heapdump">
          <div class="endpoint-card-left">
            <span class="endpoint-path">/actuator/heapdump</span>
            <span class="endpoint-desc">Heap dump — binary download</span>
          </div>
          <span class="badge badge-mgmt">MGMT</span>
        </a>
      </div>
    </div>

  </div>

  <footer>
    Built with <strong>Spring Boot</strong> &nbsp;&bull;&nbsp;
    <a href="/profile">About the author</a> &nbsp;&bull;&nbsp;
    <a href="/links">All endpoints</a> &nbsp;&bull;&nbsp;
    <code>/api/sre/runtime</code>
  </footer>

</body>
</html>
                """;
    }
}
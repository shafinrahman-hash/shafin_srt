package dev.shafin.reliability_hub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinksController {

    @GetMapping(value = "/links", produces = "text/html")
    public String links() {
        return """
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Quick Links – Reliability Hub</title>
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
      --amber: #fbbf24;
      --violet: #a78bfa;
    }
    body { font-family: 'Inter', sans-serif; background: var(--bg); color: var(--text); min-height: 100vh; }

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
    .nav-links { display: flex; gap: 8px; }
    .nav-links a {
      padding: 6px 14px; border-radius: 6px; font-size: 0.875rem; font-weight: 500;
      color: var(--muted); text-decoration: none; transition: color .15s, background .15s;
    }
    .nav-links a:hover { color: var(--text); background: var(--surface2); }
    .nav-links a.active { color: var(--text); background: var(--surface2); }

    /* PAGE HEADER */
    .page-header { max-width: 860px; margin: 56px auto 40px; padding: 0 24px; }
    .page-header .back {
      display: inline-flex; align-items: center; gap: 6px;
      font-size: 0.82rem; color: var(--muted); text-decoration: none;
      margin-bottom: 20px; transition: color .15s;
    }
    .page-header .back:hover { color: var(--text); }
    .page-header h1 { font-size: 1.75rem; font-weight: 700; letter-spacing: -0.8px; color: #fff; margin-bottom: 8px; }
    .page-header p { font-size: 0.9rem; color: var(--muted); }

    /* SECTION */
    .content { max-width: 860px; margin: 0 auto 80px; padding: 0 24px; display: flex; flex-direction: column; gap: 40px; }

    .section-label {
      font-size: 0.7rem; font-weight: 700; letter-spacing: 1.4px;
      text-transform: uppercase; color: var(--muted); margin-bottom: 14px;
      display: flex; align-items: center; gap: 10px;
    }
    .section-label::after { content: ''; flex: 1; height: 1px; background: var(--border); }

    /* LINK CARDS */
    .link-list { display: flex; flex-direction: column; gap: 10px; }
    .link-card {
      display: flex; align-items: center; justify-content: space-between; gap: 16px;
      background: var(--surface); border: 1px solid var(--border); border-radius: 10px;
      padding: 14px 18px; text-decoration: none;
      transition: border-color .2s, background .2s, transform .15s;
    }
    .link-card:hover { border-color: var(--accent); background: var(--surface2); transform: translateX(3px); }
    .link-card-left { display: flex; flex-direction: column; gap: 3px; min-width: 0; }
    .link-card-path {
      font-size: 0.9rem; font-weight: 600; color: var(--text);
      font-family: 'Inter', monospace; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;
    }
    .link-card-desc { font-size: 0.8rem; color: var(--muted); }
    .link-card-right { display: flex; align-items: center; gap: 8px; flex-shrink: 0; }

    /* BADGES */
    .badge {
      font-size: 0.68rem; font-weight: 700; letter-spacing: 0.5px;
      padding: 3px 9px; border-radius: 5px; white-space: nowrap;
    }
    .badge-get  { background: #0d2e20; color: var(--green);  border: 1px solid #1a4d35; }
    .badge-mgmt { background: #2d2008; color: var(--amber);  border: 1px solid #4d3810; }
    .badge-ext  { background: #1e1040; color: var(--violet); border: 1px solid #352066; }

    /* ARROW ICON */
    .arrow { color: var(--muted); font-size: 1rem; transition: color .15s; }
    .link-card:hover .arrow { color: var(--accent); }

    /* FOOTER */
    footer {
      text-align: center; padding: 24px; border-top: 1px solid var(--border);
      font-size: 0.8rem; color: var(--muted);
    }
    footer code { background: var(--surface2); padding: 2px 7px; border-radius: 5px; }
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
      <a href="/profile">Profile</a>
      <a href="/links" class="active">Quick Links</a>
    </div>
  </nav>

  <div class="page-header">
    <h1>Quick Links</h1>
    <p>All available URLs exposed by this application.</p>
  </div>

  <div class="content">

    <!-- EXTERNAL -->
    <div>
      <div class="section-label">External</div>
      <div class="link-list">
        <a class="link-card" href="https://www.linkedin.com/in/shafint/" target="_blank">
          <div class="link-card-left">
            <span class="link-card-path">linkedin.com/in/shafint</span>
            <span class="link-card-desc">Professional profile &amp; work history</span>
          </div>
          <div class="link-card-right">
            <span class="badge badge-ext">EXT</span>
            <span class="arrow">&#8599;</span>
          </div>
        </a>
      </div>
    </div>

    <!-- SRE API -->
    <div>
      <div class="section-label">SRE API &nbsp;/api/sre &nbsp;&middot;&nbsp; SreController.java</div>
      <div class="link-list">
        <a class="link-card" href="/api/sre/ping">
          <div class="link-card-left">
            <span class="link-card-path">/api/sre/ping</span>
            <span class="link-card-desc">Returns status and current server time</span>
          </div>
          <div class="link-card-right">
            <span class="badge badge-get">GET</span>
            <span class="arrow">&#8594;</span>
          </div>
        </a>
        <a class="link-card" href="/api/sre/runtime">
          <div class="link-card-left">
            <span class="link-card-path">/api/sre/runtime</span>
            <span class="link-card-desc">JVM runtime stats — uptime, heap usage, processors</span>
          </div>
          <div class="link-card-right">
            <span class="badge badge-get">GET</span>
            <span class="arrow">&#8594;</span>
          </div>
        </a>
      </div>
    </div>

    <!-- ACTUATOR -->
    <div>
      <div class="section-label">Actuator &nbsp;/actuator &nbsp;&middot;&nbsp; application.yml</div>
      <div class="link-list">
        <a class="link-card" href="/actuator/health">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/health</span>
            <span class="link-card-desc">Liveness &amp; readiness probes</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/info">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/info</span>
            <span class="link-card-desc">Application info</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/metrics">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/metrics</span>
            <span class="link-card-desc">Micrometer metrics index</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/prometheus">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/prometheus</span>
            <span class="link-card-desc">Prometheus scrape endpoint</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/loggers">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/loggers</span>
            <span class="link-card-desc">Logger levels — read &amp; update at runtime</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/threaddump">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/threaddump</span>
            <span class="link-card-desc">JVM thread dump</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/heapdump">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/heapdump</span>
            <span class="link-card-desc">JVM heap dump — binary download</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
        <a class="link-card" href="/actuator/env">
          <div class="link-card-left">
            <span class="link-card-path">/actuator/env</span>
            <span class="link-card-desc">Environment properties &amp; config sources</span>
          </div>
          <div class="link-card-right"><span class="badge badge-mgmt">MGMT</span><span class="arrow">&#8594;</span></div>
        </a>
      </div>
    </div>

  </div>

  <footer>
    Built with <strong>Spring Boot</strong> &nbsp;&bull;&nbsp; <code>application.yml</code> &nbsp;&bull;&nbsp; <code>SreController.java</code>
  </footer>

</body>
</html>
                """;
    }
}

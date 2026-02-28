package dev.shafin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/sre")
public class SreController {

    @GetMapping("/ping")
    public Map<String, Object> ping() {
        return Map.of(
                "status", "ok",
                "time", Instant.now().toString()
        );
    }

    @GetMapping("/runtime")
    public Map<String, Object> runtime() {
        Runtime rt = Runtime.getRuntime();
        return Map.of(
                "uptime_ms", ManagementFactory.getRuntimeMXBean().getUptime(),
                "processors", rt.availableProcessors(),
                "heap_used_bytes", (rt.totalMemory() - rt.freeMemory()),
                "heap_total_bytes", rt.totalMemory(),
                "heap_max_bytes", rt.maxMemory()
        );
    }
}
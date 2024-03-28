package com.example.jymapplication.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        double freeMemoryPercentage = ((double) freeMemory / totalMemory) * 100;

        if (freeMemoryPercentage > 10) {
            return Health.up().withDetail("OK","A lot of memory enable").build();
        } else {
            return Health.down().withDetail("Error", "Low memory available").build();
        }
    }
}

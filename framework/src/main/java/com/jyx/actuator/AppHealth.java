package com.jyx.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AppHealth implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = 0;//check(); // 定义自己的监控验证方法，具体实现略
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
}
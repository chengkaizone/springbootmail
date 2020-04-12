package com.smile.springbootmail.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class SangHealth implements HealthIndicator {

    @Override
    public Health health() {

        // 这里需要根据条件配置，且只能返回之后配置的一种状态信息
//        return Health.up().withDetail("msg", "网络连接正常...")
//                .build();
        return Health.status("FATAL").withDetail("msg", "网络断开...").build();
    }
}

package com.smile.springbootmail.controller;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SangInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> info = new HashMap<>();
        info.put("name", "江南一点雨");
        info.put("email", "123@163.com");
        builder.withDetail("author", info);
    }

}

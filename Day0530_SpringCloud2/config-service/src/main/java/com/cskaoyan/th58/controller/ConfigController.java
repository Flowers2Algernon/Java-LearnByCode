package com.cskaoyan.th58.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${call.path}")
    String path;

    @Value("${call.user.name}")
    String name;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("config/call")
    public String call() {
        // 拼接url路径和参数
        String url = "http://config-provider" + path + "?name={1}";
        // 调用config-provider服务
        return restTemplate.getForObject(url, String.class, name);
    }
}
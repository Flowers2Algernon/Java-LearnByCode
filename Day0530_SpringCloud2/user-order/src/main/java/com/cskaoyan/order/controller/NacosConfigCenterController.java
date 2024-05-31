package com.cskaoyan.order.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosConfigCenterController {
    @Value("${nacos.config}")
    String config;
    @GetMapping("nacos/config")
    public String nacosConfig(){
        return config;
    }
}

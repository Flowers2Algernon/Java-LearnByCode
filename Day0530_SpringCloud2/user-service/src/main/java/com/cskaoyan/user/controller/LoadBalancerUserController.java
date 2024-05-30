package com.cskaoyan.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoadBalancerUserController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("loadbalancer")
    public String testLoadBalancer(String name) {
        return port + ": hello, " + name;
    }
}

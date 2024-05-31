package com.cskaoyan.th58.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigProviderController {

    @GetMapping("hello")
    public String hello(String name) {
        return "hello, " + name;
    }

    @GetMapping("abuse")
    public String abuse(String name) {
        return "shit, " + name;
    }

}
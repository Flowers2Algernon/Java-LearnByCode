package com.cskaoyan.user.controller;


import com.cskaoyan.th58.model.User;
import com.cskaoyan.user.service.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class FeignUserController {
    @Autowired
    FeignUserService feignUserService;
    @GetMapping("feign/queryString")
    public String testQueryStringParam(String name) {
        return "hello, " + name;
    }

    @GetMapping("feign/obj")
    public User testObjParam(Long id) {
        return feignUserService.getUserById(id);
    }
}

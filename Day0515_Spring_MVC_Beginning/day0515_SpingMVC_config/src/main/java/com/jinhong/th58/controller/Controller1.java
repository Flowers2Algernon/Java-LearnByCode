package com.jinhong.th58.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller1 {

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        return "hello";
    }
}

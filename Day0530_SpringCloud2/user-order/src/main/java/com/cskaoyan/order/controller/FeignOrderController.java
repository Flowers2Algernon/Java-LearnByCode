package com.cskaoyan.order.controller;

import com.cskaoyan.order.client.UserServiceClient;
import com.cskaoyan.order.model.Order;
import com.cskaoyan.order.service.FeignOrderService;
import com.cskaoyan.th58.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("order")
public class FeignOrderController {


    @Autowired
    FeignOrderService feignOrderService;

    @GetMapping("feign/{id}")
    public Order basicFeign(@PathVariable Long id) {
        return feignOrderService.feignQueryById(id);
    }

    // 测试 queryString参数
    @GetMapping("feign/test/param")
    public String testQueyString(String name) {
        return feignOrderService.testQueyStringParam(name);
    }

    @Autowired
    UserServiceClient userServiceClient;
    @GetMapping("feign/obj")
    public User feignObj(Long id) {
        return userServiceClient.testObjParam(id);
    }

}
package com.cskaoyan.order.controller;

import com.cskaoyan.order.model.Order;
import com.cskaoyan.order.service.RegisterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class RegistryOrderController {
    @Autowired
    RegisterOrderService registerOrderService;

    @GetMapping("register/{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") long orderId){
        return registerOrderService.queryById(orderId);
    }
}

package com.cskaoyan.order.service;

import com.cskaoyan.order.mapper.OrderMapper;
import com.cskaoyan.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadBalanceOrderService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderMapper orderMapper;
    public Order queryById(Long orderId) {
//        RestTemplate restTemplate = new RestTemplate();
        Order order = orderMapper.findById(orderId);
        String url = "http//user-service/user/address/{id}";
        String address = restTemplate.getForObject(url,String.class,order.getUserId());
        order.setAddress(address);
        return order;
    }
}

package com.cskaoyan.order.service;

import com.cskaoyan.order.mapper.OrderMapper;
import com.cskaoyan.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public Order queryOrderById(Long orderId){
        Order order = orderMapper.findById(orderId);

        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:9001/user/address/{n}";//此处调用user服务来获取当前用户的address
//        getByUserIdToObject(restTemplate, url, order);
        //接下来使用Map来操作
        Class<String> stringClass = String.class;
//        String address = getByMap(order, restTemplate, url, stringClass);
        //此处使用entity操作
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, stringClass, order.getUserId());
        System.out.println(forEntity.getStatusCode());
        String address = forEntity.getBody();
        order.setAddress(address);
        return order;
    }

    private static String getByMap(Order order, RestTemplate restTemplate, String url, Class<String> stringClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("n", order.getUserId());
        String address = restTemplate.getForObject(url, stringClass, map);
        return address;
    }

    private static void getByUserIdToObject(RestTemplate restTemplate, String url, Order order) {
        Class<String> responseClass = String.class;
        String address = restTemplate.getForObject(url, responseClass, order.getUserId());
        order.setAddress(address);
    }
}

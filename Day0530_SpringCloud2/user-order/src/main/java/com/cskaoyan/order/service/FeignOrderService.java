package com.cskaoyan.order.service;


import com.cskaoyan.order.client.UserServiceClient;
import com.cskaoyan.order.mapper.OrderMapper;
import com.cskaoyan.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserServiceClient userServiceClient;

    public Order feignQueryById(Long orderId) {

        Order order = orderMapper.findById(orderId);

        // 发起服务调用请求(以方法调用的形式来实现服务调用)
        String address = userServiceClient.queryById(order.getUserId());
        order.setAddress(address);
        return order;
    }

    /*
         url中的queryString参数
         http://ip:port?name=zs&age=18
     */
    public String testQueyStringParam(String name) {
        String s = userServiceClient.testQueryStringParam(name);
        return s;
    }

}

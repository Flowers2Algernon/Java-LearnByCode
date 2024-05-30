package com.cskaoyan.order.service;

import com.cskaoyan.order.mapper.OrderMapper;
import com.cskaoyan.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class RegisterOrderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    OrderMapper orderMapper;


    public Order queryById(long id){
        //首先查询订单
        Order order = orderMapper.findById(id);

        //调用用户服务
        //获取指导服务实例名称的服务实例列表，此处的user-service是从子工程user-service的yml配置文件中拿出的
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");

        //选择一个服务实例，此处采用最低级选择策略
        ServiceInstance serviceInstance = instances.get(0);

        RestTemplate restTemplate = new RestTemplate();
        //发起服务调用请求
        //注意此处没有将主机和端口写死了
        String uri = serviceInstance.getUri()+"/user/address/{n}";
        String address = restTemplate.getForObject(uri, String.class, order.getUserId());
        order.setAddress(address);
        return order;
    }

}

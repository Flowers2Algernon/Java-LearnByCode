package com.cskaoyan.order.controller;

import com.cskaoyan.order.model.Order;
import com.cskaoyan.order.service.LoadBalanceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class LoadBalanceOrderController {
    @Autowired
    LoadBalanceOrderService loadBalanceOrderService;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/loadbalancer/{id}")
    public Order queryByIdWithLoadBalancer(@PathVariable("id") Long id){
        return loadBalanceOrderService.queryById(id);
    }

    //以下方法来查看采用的是哪种均衡策略
    @GetMapping("/test/load")
    public String callLoadBalance(String name){
        String url = "http://user-service/user/loadbalancer?name={name}";
        int firstInstanceCount = 0;
        int secondInstanceCount = 0;
        for(int i =0;i<30;i++){
            String result = restTemplate.getForObject(url,String.class,name);

            if (result.startsWith("9001")){
                firstInstanceCount++;
            }else secondInstanceCount++;
        }
        System.out.println(firstInstanceCount+"first");
        System.out.println(secondInstanceCount+"second");
        return "loadBalance";
    }
}

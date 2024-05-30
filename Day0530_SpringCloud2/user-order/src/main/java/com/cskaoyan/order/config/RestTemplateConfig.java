package com.cskaoyan.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@LoadBalancerClient(name = "user-service",configuration = LoadBalanceConfig.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    /*
        1. 当我们加上@LoadBalanced之后，我们从容器中注入的是一个增强版的RestTemplate
        2. 增强主要表现在，RestTemplate对象中被放入了一个请求拦截器，该拦截器仅仅只会
           拦截RestTemplate所发送的请求，在请求发送之前，拦截该请求
        3. 在该拦截器中，LoadBalancer获取原始请求url中的服务名称
        4. 根据服务名称，去访问服务注册中心，根据服务名称获取所有服务实例的信息
        5. 选择其中的一个服务实例，获取该服务实例的ip地址和端口号，用来替换原始url中的服务名称
        6. 最后，RestTemplate在根据替换之后的url发起真正的请求
     */
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

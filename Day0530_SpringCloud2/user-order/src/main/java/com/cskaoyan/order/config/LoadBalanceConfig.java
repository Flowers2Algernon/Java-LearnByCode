package com.cskaoyan.order.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class LoadBalanceConfig {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> loadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory){
        //从environment对象获取服务的名称
        String serviceName = environment.getProperty(loadBalancerClientFactory.PROPERTY_NAME);

        // 从LoadBalancerClientFactory对象获取ObjectProvider<ServiceInstanceListSupplier>
        ObjectProvider<ServiceInstanceListSupplier> lazyProvider = loadBalancerClientFactory.getLazyProvider(serviceName, ServiceInstanceListSupplier.class);

        //返回负载均方法
        return  new RandomLoadBalancer(lazyProvider,serviceName);
//        return new RoundRobinLoadBalancer(lazyProvider,serviceName);
//        return new MyLoadBalancer(lazyProvider,serviceName);
    }
}

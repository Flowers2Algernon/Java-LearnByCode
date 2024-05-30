package com.cskaoyan.order.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultRequest;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

public class MyLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    String serviceId;

    ObjectProvider<ServiceInstanceListSupplier> objectProvider;

    public MyLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> objectProvider
            ,String serviceId){
        this.objectProvider= objectProvider;
        this.serviceId= serviceId;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        //先获取封装了的服务实例列表的 supplier对象
        ServiceInstanceListSupplier supplier = objectProvider.getIfAvailable();
        Mono<Response<ServiceInstance>> response = supplier.get(request).next().map(serviceInstances -> dochooseInstance(serviceInstances));
        return response;
    }

    private Response<ServiceInstance> dochooseInstance(List<ServiceInstance> serviceInstances) {
        ServiceInstance serviceInstance = serviceInstances.get(0);
        DefaultResponse defaultResponse = new DefaultResponse(serviceInstance);
        return defaultResponse;
    }
}

package com.cskaoyan.order.client;

import com.cskaoyan.th58.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceClient {
    @GetMapping("/user/address/{id}")
    public String queryById(@PathVariable("id") Long id);

    @GetMapping("/user/feign/queryString")
    public String testQueryStringParam(String name);

    @GetMapping("/user/feign/obj")
    public User testObjParam(Long id);
}

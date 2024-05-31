package com.cskaoyan.th58.client;

import com.cskaoyan.th58.model.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("shop-service")
public interface CartServiceClient {
    @GetMapping("/product/{skuId}")
    ProductInfo getProduct(@PathVariable("skuId") Long skuId);
}

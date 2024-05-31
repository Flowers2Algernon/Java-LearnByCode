package com.cskaoyan.th58.controller;

import com.cskaoyan.th58.model.ProductInfo;
import com.cskaoyan.th58.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ShopService shopService;

    // 被购物车服务调用，根据skuId获取商品信息
    @GetMapping("{skuId}")
    public ProductInfo getProduct(@PathVariable Long skuId) {
        // 访问数据库将skuId作为主键值，查询商品名称并返回
        ProductInfo productInfo = shopService.queryById(skuId);
        return productInfo;
    }
}
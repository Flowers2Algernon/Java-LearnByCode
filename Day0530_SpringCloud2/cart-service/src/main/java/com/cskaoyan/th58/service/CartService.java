package com.cskaoyan.th58.service;

import com.cskaoyan.th58.client.CartServiceClient;

import com.cskaoyan.th58.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartServiceClient cartServiceClient;

    public ProductInfo findNameById(Long id){
        return cartServiceClient.getProduct(id);
    }
}

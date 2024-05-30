package com.cskaoyan.th58.service;

import com.cskaoyan.th58.client.CartServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartServiceClient cartServiceClient;

    public String findNameById(Long id){
        return cartServiceClient.getProduct(id);
    }
}

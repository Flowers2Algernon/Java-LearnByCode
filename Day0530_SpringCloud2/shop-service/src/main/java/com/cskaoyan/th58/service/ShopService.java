package com.cskaoyan.th58.service;

import com.cskaoyan.th58.mapper.ShopMapper;
import com.cskaoyan.th58.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    ShopMapper shopMapper;
    public String queryById(Long id){
        ProductInfo productInfo = shopMapper.findById(id);
        return productInfo.getSkuName();
    }
}

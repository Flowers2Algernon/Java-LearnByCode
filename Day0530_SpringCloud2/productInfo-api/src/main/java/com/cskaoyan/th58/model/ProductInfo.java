package com.cskaoyan.th58.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    // 商品id
    private Long id;
    // 商品名称
    private String skuName;
    // 价格
    private BigDecimal price;
}
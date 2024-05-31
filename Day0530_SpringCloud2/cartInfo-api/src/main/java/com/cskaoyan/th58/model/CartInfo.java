package com.cskaoyan.th58.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {
    // 购物车条目id
    private Long id;
    // 商品skuId
    private Long skuId;

    // 商品名称
    private String skuName;
    // 商品价格
    private BigDecimal price;

    // 添加商品数量
    private Integer num;

}

package com.cskaoyan.th58.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {

    // 商品skuId
    private Long skuId;

    // 商品名称
    private String skuName;

    // 添加商品数量
    private Integer num;

}

package com.cskaoyan.order.model;


import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    // 用户的地址
    private String address;
}

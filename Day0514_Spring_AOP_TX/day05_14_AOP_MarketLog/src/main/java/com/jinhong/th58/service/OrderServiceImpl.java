package com.jinhong.th58.service;

import com.jinhong.th58.annotaion.Log;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Log(type = "订单操作", action = "发货")
    @Override
    public void ship() {

    }

    @Log(type = "订单操作", action = "删除")
    @Override
    public void delete() {

    }
}

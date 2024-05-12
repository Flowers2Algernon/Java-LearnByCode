package com.cskaoyan.th58.service;

import com.cskaoyan.th58.mapper.UserMapper;

public class OrderServiceImpl implements OrderService{
    UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
}

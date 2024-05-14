package com.jinhong.th58.service;

import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 9:48
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void addOne() {
        System.out.println("userServiceImpl addOne");
    }

    @Override
    public void selectOne() {
        System.out.println("userServiceImpl selectOne");
    }
}

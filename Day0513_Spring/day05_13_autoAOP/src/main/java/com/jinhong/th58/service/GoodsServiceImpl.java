package com.jinhong.th58.service;

import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/13 16:31
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    @Override
    public void addOne() {
        System.out.println("goodsServiceImpl addOne");
    }

    @Override
    public void selectOne() {
        System.out.println("goodsServiceImpl selectOne");
    }
}

package com.jinhong.th58.service;

import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 9:41
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    //对于当前方法进行增强
    @Override
    public void addOne() {
        System.out.println("goods service addOne");
    }

    @Override
    public void selectOne() {
        System.out.println("goods service selectOne");
    }
}

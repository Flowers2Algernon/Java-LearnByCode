package com.jinhong.th58.service;

import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService{
    @Override
    public void addOne() {
        System.out.println("goodservice addone");
    }

    @Override
    public void selectOne() {
        System.out.println("goodservice selectone");
    }
}

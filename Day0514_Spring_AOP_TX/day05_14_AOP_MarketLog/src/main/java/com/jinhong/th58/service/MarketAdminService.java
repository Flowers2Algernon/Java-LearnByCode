package com.jinhong.th58.service;


import com.jinhong.th58.domain.MarketAdmin;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 11:08
 * @Version 1.0
 */
public interface MarketAdminService {

    MarketAdmin login(String username, String password);


    void logout();
}

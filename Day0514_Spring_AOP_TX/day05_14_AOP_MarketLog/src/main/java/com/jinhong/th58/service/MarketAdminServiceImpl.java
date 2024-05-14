package com.jinhong.th58.service;


import com.jinhong.th58.annotaion.Log;
import com.jinhong.th58.domain.MarketAdmin;
import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 11:08
 * @Version 1.0
 */
@Service
public class MarketAdminServiceImpl implements MarketAdminService{

    @Log(type = "安全操作", action = "登录")
    @Override
    public MarketAdmin login(String username, String password) {


        return null;
    }



    @Log(type = "安全操作", action = "注销")
    @Override
    public void logout() {

    }
}

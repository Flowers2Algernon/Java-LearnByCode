package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;

import java.time.LocalDateTime;

public interface AdminAdminCreateService {
    MarketAdmin create(String username, String avatar, String password, Integer id, Integer[] roleIds, LocalDateTime updateTime,LocalDateTime addTime);
}

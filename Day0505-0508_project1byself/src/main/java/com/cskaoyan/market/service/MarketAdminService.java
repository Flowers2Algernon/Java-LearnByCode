package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;

public interface MarketAdminService {
    MarketAdmin login(String username,String password);
}

package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.db.domain.MarketAdminListVo;

import java.util.List;

public interface AdminAdminListService {
    List<MarketAdminListVo> list(Integer limit, Integer page, String username, String sort, String order);
}

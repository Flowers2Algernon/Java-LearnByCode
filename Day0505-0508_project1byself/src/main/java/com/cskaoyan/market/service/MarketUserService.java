package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketUser;

import java.util.List;

public interface MarketUserService {
    List<MarketUser> list(Integer limit,Integer page,String username,String mobile,Integer id,String sort,String order);
}

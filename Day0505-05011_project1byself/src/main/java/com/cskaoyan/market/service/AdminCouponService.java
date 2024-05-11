package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketCoupon;

import java.util.List;

public interface AdminCouponService {
    List<MarketCoupon> list(Integer page, Integer limit, String sort, String order, Short type, Short status, String name);


    void create(MarketCoupon marketCoupon);
}

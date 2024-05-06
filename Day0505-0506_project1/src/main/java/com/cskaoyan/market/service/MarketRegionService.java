package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketRegionVo;

import java.util.List;

public interface MarketRegionService {
    List<MarketRegionVo> list();//此时无需输入参数
    //http://39.101.189.16:8083/admin/region/list
}

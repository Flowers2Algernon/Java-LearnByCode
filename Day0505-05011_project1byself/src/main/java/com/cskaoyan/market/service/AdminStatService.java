package com.cskaoyan.market.service;

import com.cskaoyan.market.vo.AdminStatUserBo;

import java.util.List;

public interface AdminStatService {
    List<AdminStatUserBo> getDayAndUserCount();
}

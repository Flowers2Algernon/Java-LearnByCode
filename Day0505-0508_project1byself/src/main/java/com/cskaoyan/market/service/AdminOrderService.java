package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketOrder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface AdminOrderService {

    List<MarketOrder> list(Integer page, Integer limit, String orderStatus, String sort, String order, LocalDateTime start, LocalDateTime end, String orderSn, Integer orderId, Integer userId);

    HashMap<String, Object> details(Integer id);
}

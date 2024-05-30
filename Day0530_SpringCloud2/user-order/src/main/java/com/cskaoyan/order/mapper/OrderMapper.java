package com.cskaoyan.order.mapper;

import com.cskaoyan.order.model.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    Order findById(@Param("id") Long orderId);
}

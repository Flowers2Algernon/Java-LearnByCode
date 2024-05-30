package com.cskaoyan.th58.mapper;

import com.cskaoyan.th58.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

public interface ShopMapper {
    ProductInfo findById(@Param("id") Long id);
}

package com.cskaoyan.market.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketRegionVo {
    private Integer id;
    private String name;
    private Byte type;
    private Integer code;
    private List<MarketRegionVo> children;
}

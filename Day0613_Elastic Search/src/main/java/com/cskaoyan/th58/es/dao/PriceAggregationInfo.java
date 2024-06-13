package com.cskaoyan.th58.es.dao;

import lombok.Data;

import java.util.List;

@Data
public class PriceAggregationInfo {

    private double price;
    List<String> trademarkNames;

}

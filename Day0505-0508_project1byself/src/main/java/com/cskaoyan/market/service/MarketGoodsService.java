package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketGoods;
import com.cskaoyan.market.db.domain.MarketGoodsAttribute;
import com.cskaoyan.market.db.domain.MarketGoodsProduct;
import com.cskaoyan.market.db.domain.MarketGoodsSpecification;

import java.util.List;
import java.util.Map;

public interface MarketGoodsService {
   void create(MarketGoods marketGoods, List<MarketGoodsSpecification> marketGoodsSpecifications, List<MarketGoodsAttribute> attributes, List<MarketGoodsProduct> products);

   List<MarketGoods> list(Integer limit, Integer page, String goodsSn, String goodsId, String order, String sort, String name);

   Map<String,Object> catAndBrand();
}

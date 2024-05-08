package com.cskaoyan.market.bo;

import com.cskaoyan.market.db.domain.MarketGoods;
import com.cskaoyan.market.db.domain.MarketGoodsAttribute;
import com.cskaoyan.market.db.domain.MarketGoodsProduct;
import com.cskaoyan.market.db.domain.MarketGoodsSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGoodsBo {
    private MarketGoods goods;
    private List<MarketGoodsAttribute> attributes;
    private List<MarketGoodsProduct> products;
    private List<MarketGoodsSpecification> specifications;
}

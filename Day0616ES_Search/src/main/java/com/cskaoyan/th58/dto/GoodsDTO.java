package com.cskaoyan.th58.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoodsDTO {

    // 商品Id skuId
    private Long id;

    private String defaultImg;

    private String title;

    private Double price;

    private Long tmId;

    private String tmName;

    private String tmLogoUrl;


    private Long firstLevelCategoryId;

    private String firstLevelCategoryName;

    private Long secondLevelCategoryId;


    private String secondLevelCategoryName;

    private Long thirdLevelCategoryId;


    private String thirdLevelCategoryName;

    //  商品的热度！ 我们将商品被用户点查看的次数越多，则说明热度就越高！

    private Long hotScore = 0L;

    // 平台属性集合对象
    // Nested 支持嵌套查询
    private List<SearchAttrDTO> attrs;
}

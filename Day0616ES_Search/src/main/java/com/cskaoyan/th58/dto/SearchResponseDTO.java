package com.cskaoyan.th58.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResponseDTO implements Serializable {

    // 品牌数据集合
    private List<SearchResponseTmDTO> trademarkList;

    // 平台属性数据的集合
    private List<SearchResponseAttrDTO> attrsList;

    // 文档数据的集合
    private List<GoodsDTO> goodsList = new ArrayList<>();

    // 查询总条数
    private Long total;

}

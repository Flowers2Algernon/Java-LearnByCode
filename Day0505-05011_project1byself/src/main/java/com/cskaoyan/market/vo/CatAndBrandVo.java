package com.cskaoyan.market.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatAndBrandVo {
    private String label;
    private Integer value;
   private List<CatAndBrandVo> children;
}

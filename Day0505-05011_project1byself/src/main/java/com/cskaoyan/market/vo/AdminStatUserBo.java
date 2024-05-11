package com.cskaoyan.market.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatUserBo {
    private String day;
    private Integer userCount;
}

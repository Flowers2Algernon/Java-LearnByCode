package com.cskaoyan.th58.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.PrimitiveIterator;

@Data
@TableName("employee")
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Integer departmentId;
    private String status;
    private BigDecimal performanceScore;
    private Integer yearsOfService;
    private String level;
}

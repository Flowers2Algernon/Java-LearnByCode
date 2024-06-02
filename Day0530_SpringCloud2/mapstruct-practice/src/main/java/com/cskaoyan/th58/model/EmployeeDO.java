package com.cskaoyan.th58.model;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDO {
    private Long id;
    private String fullName;
    private Integer age;
    private List<JobDO> jobHistory; // 员工职位历史
}
package com.cskaoyan.th58.dto;

import lombok.Data;

import java.util.List;
@Data
public class EmployeeDTO {
    private Long id;
    private String name; // 对应EmployeeDO的fullName
    private Integer age; // 保持一致
    private List<JobDTO> jobHistory; // 对应EmployeeDO的jobHistory，但使用JobDTO类型
}
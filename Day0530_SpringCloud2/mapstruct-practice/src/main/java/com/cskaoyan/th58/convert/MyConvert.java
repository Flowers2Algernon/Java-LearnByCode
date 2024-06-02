package com.cskaoyan.th58.convert;

import com.cskaoyan.th58.dto.EmployeeDTO;
import com.cskaoyan.th58.dto.JobDTO;
import com.cskaoyan.th58.model.EmployeeDO;
import com.cskaoyan.th58.model.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyConvert {
    //在里面定义转换方法

    @Mapping(source = "title",target = "jobTitle")
    @Mapping(source = "department",target ="deptName")
    JobDTO jobDO2JobDTO(JobDO jobDO);

    @Mapping(source = "fullName",target = "name")
    EmployeeDTO employeeDO2EmployeeDTO(EmployeeDO employeeDO);
}

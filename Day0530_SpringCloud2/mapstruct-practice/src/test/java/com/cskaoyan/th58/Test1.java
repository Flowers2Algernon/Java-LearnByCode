package com.cskaoyan.th58;

import com.cskaoyan.th58.convert.MyConvert;
import com.cskaoyan.th58.dto.EmployeeDTO;
import com.cskaoyan.th58.dto.JobDTO;
import com.cskaoyan.th58.model.EmployeeDO;
import com.cskaoyan.th58.model.JobDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Test1 {
    @Autowired
    MyConvert myConvert;

    @Test
    public void test1(){
        JobDO jobDO = new JobDO();
        jobDO.setDepartment("math");
        jobDO.setTitle("RA");
        JobDTO jobDTO = myConvert.jobDO2JobDTO(jobDO);
        System.out.println(jobDTO);
    }
    @Test
    public void test2(){
        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setAge(18);
        employeeDO.setId(10L);
        employeeDO.setFullName("zs");
        employeeDO.setJobHistory(Arrays.asList(new JobDO("professor","cs"),new JobDO("TA","math")));
        EmployeeDTO employeeDTO = myConvert.employeeDO2EmployeeDTO(employeeDO);
        System.out.println(employeeDTO);
    }

}

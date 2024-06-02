package com.cskaoyan.th58;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cskaoyan.th58.mapper.EmployeeMapper;
import com.cskaoyan.th58.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Test1 {
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void test1(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.between("age",30,50).in("department_id",2,5,7).in("status","在职").ge("performance_score",80).ge("years_of_service",3);
        List<Employee> employees =
                employeeMapper.selectList(wrapper);
        System.out.println(employees);
    }
    @Test
    public void test2(){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.ge("age",40).in("status","在职").lt("performance_score",80);
        //准备新的值：
        Employee employee = new Employee();
        employee.setStatus("观察期");
        employee.setLevel("初级");
        int update = employeeMapper.update(employee, wrapper);
        System.out.println(update);
    }
}

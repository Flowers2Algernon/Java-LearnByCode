package com.jinhong.th58;


import com.jinhong.th58.controller.Controller1;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//需要扫描除了COntroller以外的其他组件,需要将Controller和SpringMVC排除在外
@ComponentScan(value = "com.jinhong.th58",excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Controller1.class), @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class)})
public class SpringConfig {
    //mybatis中涉及的所有组件，均上传到这里
}

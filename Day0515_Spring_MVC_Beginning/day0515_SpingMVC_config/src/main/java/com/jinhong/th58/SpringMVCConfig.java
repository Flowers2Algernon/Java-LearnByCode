package com.jinhong.th58;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//进行组件的实例化、用来去处理web请求
@EnableWebMvc
//SpringMVC中的组件，交给springMVC 去扫描
@ComponentScan("com.jinhong.th58.controller")
//此时还可以再实现一个接口，后续配置web组件时会更方便
public class SpringMVCConfig implements WebMvcConfigurer {
    //涉及到需要文件上传的组件，写在这个配置类中
}

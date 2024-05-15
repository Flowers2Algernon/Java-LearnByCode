package com.jinhong.th58;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
//扫描Web组件和controller
@ComponentScan("com.jinhong.th58.controller")
public class SpringMVCConfig implements WebMvcConfigurer {
}

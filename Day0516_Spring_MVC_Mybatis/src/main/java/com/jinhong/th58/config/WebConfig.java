package com.jinhong.th58.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan("com.jinhong.th58.controller")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public MultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }
}
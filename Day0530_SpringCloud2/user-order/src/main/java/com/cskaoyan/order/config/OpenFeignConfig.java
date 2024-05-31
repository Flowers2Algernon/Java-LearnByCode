package com.cskaoyan.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }
}

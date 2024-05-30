package com.cskaoyan.th58;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.cskaoyan.th58.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class,args);
    }
}

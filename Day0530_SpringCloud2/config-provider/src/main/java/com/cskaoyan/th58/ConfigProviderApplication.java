package com.cskaoyan.th58;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@MapperScan("com.cskaoyan.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ConfigProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigProviderApplication.class,args);
    }
}

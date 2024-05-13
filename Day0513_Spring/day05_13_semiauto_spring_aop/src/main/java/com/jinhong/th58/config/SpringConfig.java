package com.jinhong.th58.config;

import com.jinhong.th58.service.GoodsService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.jinhong.th58")
public class SpringConfig {
    @Bean
    public ProxyFactoryBean goodsServiceProxy(GoodsService goodsService){
        ProxyFactoryBean bean = new ProxyFactoryBean();
        //设置委托类 GoodsService
        bean.setTarget(goodsService);
        bean.setInterceptorNames("logAdvice");
        return bean;
    }
}

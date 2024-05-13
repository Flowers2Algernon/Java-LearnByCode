package com.jinhong.th58.processor;

import com.jinhong.th58.util.ProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class ServiceEnhance implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.endsWith("ServiceImpl")){
            bean = ProxyUtils.getProxy(bean);
        }
        return bean;
    }
}

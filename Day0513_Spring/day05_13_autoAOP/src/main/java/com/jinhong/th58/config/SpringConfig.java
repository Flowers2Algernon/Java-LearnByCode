package com.jinhong.th58.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.jinhong.th58")
//开启aspectj支持
@EnableAspectJAutoProxy
public class SpringConfig {
}

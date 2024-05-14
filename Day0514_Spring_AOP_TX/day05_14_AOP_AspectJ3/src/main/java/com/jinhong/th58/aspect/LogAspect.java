package com.jinhong.th58.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(com.jinhong.th58.annotation.Log)")
    public void pt1(){}

    //增强
    @Before("pt1()")
    public void beforeAdvice(){
        System.out.println("这是前置增强");
    }
}

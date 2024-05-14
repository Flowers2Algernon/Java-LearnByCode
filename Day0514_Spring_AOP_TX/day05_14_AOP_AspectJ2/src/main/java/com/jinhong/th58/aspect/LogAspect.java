package com.jinhong.th58.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    //* com.jinhong.th58.*.service.*ServiceImpl.add*(..) --明显可以看出此处多了一级目录
    //* com.jinhong.*.service.*ServiceImpl.add*(..)
    @Pointcut("execution(* com.jinhong.*.service.*ServiceImpl.add*(..))")
    public void pt1(){}

    //通知
    @Before("pt1()")
    public void beforeAdvice(){
        System.out.println("这是一个前置通知");
    }
}

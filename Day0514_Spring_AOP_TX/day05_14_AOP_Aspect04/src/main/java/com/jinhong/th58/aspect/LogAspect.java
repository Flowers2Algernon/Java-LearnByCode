package com.jinhong.th58.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.jinhong.th58.annotation.Log)")
    public void pt1(){}


    //这次来个后置通知
    @After("pt1()")
    public void afterAdvice(){
        System.out.println("这是一个后置通知");
    }

    //来个环绕通知
    @Around("pt1()")
    public Object arounAdvice(ProceedingJoinPoint joinPoint){
        System.out.println("这是环绕通知前");
        Object proceed = null;
        try{
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("这是环绕通知后");
        return proceed;
    }


    @AfterThrowing(value = "pt1()",throwing = "exp")
    public void expAdvice(Exception exp){
        System.out.println("这是一个异常通知");
    }
}

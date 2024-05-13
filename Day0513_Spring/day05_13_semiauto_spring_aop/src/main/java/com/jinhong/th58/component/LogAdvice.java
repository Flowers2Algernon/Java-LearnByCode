package com.jinhong.th58.component;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class LogAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //MethodInterceptor可以理解为对于动态代理模式中的method的进一步封装
        System.out.println("log before");
        //以下可以看成是对method.invoke的进一步封装，
        //不足的是如果只想对某些方法进行增强，那么仍然需要分发
        Object proceed = invocation.proceed();
        System.out.println("log after");
        return proceed;
    }
}

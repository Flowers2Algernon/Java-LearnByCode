package com.jinhong.th58.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
    @Autowired
    TransactionTemplate transactionTemplate;
    @Pointcut("@annotation(com.jinhong.th58.log.Log)")
    public void pt1(){}

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object proceed = null;
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    proceed = joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
        return proceed;
    }
}

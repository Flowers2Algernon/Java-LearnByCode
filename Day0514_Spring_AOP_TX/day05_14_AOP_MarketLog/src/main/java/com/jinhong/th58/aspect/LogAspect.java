package com.jinhong.th58.aspect;

import com.jinhong.th58.annotaion.Log;
import com.jinhong.th58.domain.LogReflect;
import com.jinhong.th58.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.jinhong.th58.annotaion.Log)")
    public void pt1(){}

    @AfterReturning(value = "pt1()",returning = "result")
    public void result(JoinPoint joinPoint,Object result){
        //借助于反射来获取实现类头上注解里面的值
        Object target = joinPoint.getTarget();
        //得到委托类的Class
        Class<?> aClass = target.getClass();

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        MethodSignature methodSignature = (MethodSignature) signature;

        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();

        Method method = null;
        try {
            method = aClass.getMethod(methodName,parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Log log = method.getAnnotation(Log.class);
        String type = log.type();
        String action = log.action();
        LogReflect logReflect = new LogReflect();
        logReflect.setAction(action);
        logReflect.setAdd_time(LocalDateTime.now());
        logReflect.setOperation_method(type);
        logReflect.setError_msg("");
        logReflect.setSuccess(true);
        //此时得到注解头上的值，将其写入数据库中
        logService.addLog(logReflect);
    }

}

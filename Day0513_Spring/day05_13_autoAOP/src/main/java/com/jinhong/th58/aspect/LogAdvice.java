package com.jinhong.th58.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
//因为编写的是一个切面，所以需要额外添加一个aspect注解
public class LogAdvice {
    //切面==切入点(execution) +增强

    //新建一个空的方法，修饰符Public，返回值void
    //PointCut括号里面是具体的匹配逻辑，匹配上的则执行具体的增强
    @Pointcut("execution(* com.jinhong.th58..*ServiceImpl.add*(..))")
    public void pt1(){}

    //before表示的就是当前的一个前置通知
    @Before("pt1()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
}

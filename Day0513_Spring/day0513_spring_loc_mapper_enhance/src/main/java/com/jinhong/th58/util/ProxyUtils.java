package com.jinhong.th58.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtils {
    public static Object getProxy(Object target){
        Class<?> aClass = target.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                SqlSession session = MybatisUtils.getSession();
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.getName().endsWith("Mapper")){
                        //认为其是一个Mapper
                        declaredField.setAccessible(true);
                        Object mapper = session.getMapper(declaredField.getType());
                        //赋值
                        declaredField.set(target,mapper);
                    }
                }
                Object invoke = method.invoke(target, args);
                session.commit();
                session.close();
                return invoke;
            }
        });
    }
}

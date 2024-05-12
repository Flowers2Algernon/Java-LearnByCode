package com.cskaoyan.th58.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperEnhancerProxyUtils {
    public static Object getProxyInstances(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //代理类对象的方法在调用时，都会进一步去调用委托类里面的同名方法
                //我们需要做的事情就是在controller里面调用代理类对象的list,addGoods方法

                //在前面获取session
                SqlSession session = MybatisUtils.getSession();

                //委托类方法的调用
                Object invoke = null;
                try {
                    //首先需要获取委托类对象，获取委托类对象里面的成员变量
                    Class<?> aClass = target.getClass();
                    //拿到所有的成员变量
                    Field[] declaredFields = aClass.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        //只要确定成员变量是一个mapper，那么就对其进行赋值操作
                        if (declaredField.getName().endsWith("Mapper")){
                            //不太严谨的做法，此处若成员变量的名称以mapper结尾，则认为其是一个mapper
                            //field成员变量有可能是private的
                            //所以对其设定setaccessible
                            declaredField.setAccessible(true);
                            //以下解决对哪个对象里面的成员变量来赋值，赋何值
                            Object mapper = session.getMapper(declaredField.getType());
                            declaredField.set(target, mapper);
                        }
                    }
                    invoke = method.invoke(target,args);
                    session.commit();
                }catch (Exception e){
                    session.rollback();
                    e.printStackTrace();
                }finally {
                    session.close();
                }
                return invoke;
            }
        });
    }
}

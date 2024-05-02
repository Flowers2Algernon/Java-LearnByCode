package com.jinhong.th58.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener1 implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //当应用启动时，会调用该方法
        System.out.println("context init");
        //如果有一些全局性的代码，可以写在此处
        ServletContext servletContext = sce.getServletContext();
        //sce中的这个方法是获取context域
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //当应用卸载时，会调用该方法
    }
}

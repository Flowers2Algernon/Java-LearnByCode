package com.jinhong.th58.loginfilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebListener
public class Listener implements ServletContextListener {
    //读取位于classpath目录下的黑名单文件

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream inputStream = Listener.class.getClassLoader().getResourceAsStream("auth.txt");
        //此处的auth.txt需要放入resource里面，而不是webapp页面
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ArrayList<String> strings = new ArrayList<>();
        String line = null;
        try {
            while ((line=bufferedReader.readLine())!=null){
                strings.add(line);
            }
            ServletContext servletContext = sce.getServletContext();
            servletContext.setAttribute("stringss",strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

package com.jinhong.th58;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringInitial extends AbstractAnnotationConfigDispatcherServletInitializer {
    //配置Spring的启动类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }
    //配置SpringMVC启动类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCConfig.class};
    }
    //配置SpringMVC的核心控制类DispathcerServlet的url-pattern
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

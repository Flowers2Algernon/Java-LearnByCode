package com.jinhong.th58;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCInitial extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCConfig.class};
    }
    //表示SpringMVC的核心类DispatcherServlet的url-pattern的映射
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

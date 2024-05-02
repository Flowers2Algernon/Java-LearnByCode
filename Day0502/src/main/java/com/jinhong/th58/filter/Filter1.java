package com.jinhong.th58.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter("/dawsawsa")
public class Filter1 implements Filter {
    //应用启动的时候便会调用Init方法，当前filter被创建的时候会调用init,说明filter在运行期间也只有一个实例对象
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }
    //此方法蕾仕于servlet的service方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //应用卸载的时候会调用该方法
    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}

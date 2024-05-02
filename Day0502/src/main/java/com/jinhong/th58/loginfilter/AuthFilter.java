package com.jinhong.th58.loginfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
        //init中没有resq 和 rep,无法通过init设置编码格式
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //此时需要判断当前访问的是哪个资源
        //当访问的资源是/user/info时需要进行拦截
//        System.out.println("doo");

        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) resp;
        String requestURI = servletRequest.getRequestURI();

        //此时读取的servletContext里面有黑名单的类容
        ServletContext servletContext = req.getServletContext();
        ArrayList<String> strings = (ArrayList<String>) servletContext.getAttribute("stringss");
        String replace = requestURI.replace(servletRequest.getContextPath(), "");
        System.out.println(replace);
        HttpSession session = servletRequest.getSession();
        Object username = session.getAttribute("username");
        for (String string : strings) {
            if (replace.equals(string) && username == null) {
                //此时需要判断用户是否登录了，如果没有登录则不能进行访问
                servletResponse.sendRedirect(servletRequest.getContextPath() + "/login.html");
                return;
            }
        }
        filterChain.doFilter(req, resp);

//        //以下是未设置黑名单之前的方法
//        if (requestURI.contains("/user/info")){
//            //此时需要判断是否是登录状态--怎么判断？
//            //通过在登录状态时往session中写入用户名，此时取出session域来判断有没有用户名，有则为登录，没有则为未登录
//            HttpSession session = servletRequest.getSession();
//            Object username = session.getAttribute("username");
//            if (username == null){
//                //此时没有登录
//                servletResponse.getWriter().println("用户未登录，请重新登录");
//                servletResponse.sendRedirect(servletRequest.getContextPath()+"/login.html");
//                return;
//            }
//            //此时已登录
//            //直接放行
//            filterChain.doFilter(req,resp);
    }


    @Override
    public void destroy() {
        System.out.println("filter de1");
    }
}

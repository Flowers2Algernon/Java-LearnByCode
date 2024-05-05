package com.cskaoyan.market.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //this filter aims to tell the servlet all the request from any client can get touch in servlet
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //the above method is to transfer the type from ServletRequest to HttpServletRequest and so on
        response.setHeader("Access-Control-Allow-Origin","http://localhost:9527");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type,X-CskaoyanMarket-Admin-Token,X-CskaoyanMarket-Token");
        response.setHeader("Access-Control-Allow-Credentials","true");
        //set these modify go into effect
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

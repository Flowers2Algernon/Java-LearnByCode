package com.cskaoyan.th58.refresh;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login1")
public class Login extends HttpServlet {
    //首先校验用户输入的账户和密码是否正确
    //获取请求体中的账户和密码内容--与数据库中的内容进行匹配
    //如果匹配成功，则重定向到另外一个登录后的页面

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //数据库校验工作

        //重定向
        resp.getWriter().println("登录成功，即将跳转到页面，如果没有跳转，请点击以下链接"+"<a href='"+req.getContextPath()+"/1.jpg"+"'>点击跳转</a>");
        resp.setHeader("refresh","5;url="+req.getContextPath()+"/1.jpg");
    }
}

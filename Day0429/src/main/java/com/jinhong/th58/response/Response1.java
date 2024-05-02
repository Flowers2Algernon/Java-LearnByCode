package com.cskaoyan.th58.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/response1")
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此类中简要介绍Response的几个使用
        //设置响应行里面的状态码
        resp.setStatus(404);
        //设置响应头
        resp.setHeader("Server","Jin's Foundation");
        //设置响应体
        resp.getWriter().println("hello servlet");
    }
}

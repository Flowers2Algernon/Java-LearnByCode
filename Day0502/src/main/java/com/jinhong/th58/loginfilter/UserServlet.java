package com.jinhong.th58.loginfilter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    //分发逻辑
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("login".equals(replace)){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处显示登录成功后的欢迎页面以及将信息放入Session域中
        String username = req.getParameter("username");
        resp.getWriter().println("登录成功，欢迎 "+username);
        HttpSession session = req.getSession();
        session.setAttribute("username",username);
        //进行页面跳转
        resp.setHeader("refresh","3;url="+req.getContextPath()+"/user/info");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此处分发显示信息逻辑
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("info".equals(replace)){
            info(req,resp);
        }else if ("logout".equals(replace)){
            logout(req,resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处实现注销逻辑--所谓的注销是指将用户的信息从session域中拿出，后续session域中无内容时就能使用filter来阻止外部访问info页面
        HttpSession session = req.getSession();
        session.invalidate();
        resp.getWriter().println("注销成功，即将跳转到登录页面");
        resp.setHeader("refresh","2;url="+req.getContextPath()+"/login.html");
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //用户信息已在Login页面放入session域，此时再次从session域中获取
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        resp.getWriter().println("welcome "+username+"<a href='"+req.getContextPath()+"/user/logout"+"'>点击注销</a>");
    }
}

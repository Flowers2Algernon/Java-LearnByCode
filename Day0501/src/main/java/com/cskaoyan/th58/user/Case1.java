package com.cskaoyan.th58.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class Case1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("login".equals(replace)){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("username",username);
        resp.setHeader("refresh","2;url="+req.getContextPath()+"/user/info");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("info".equals(replace)){
            info(req,resp);
        }else if ("logout".equals(replace)){
            logout(req,resp);
        }

    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        resp.getWriter().println("welcome "+username+"<a href='"+req.getContextPath()+"/user/logout"+"'>点击注销</a>");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.setHeader("refresh","2;url="+req.getContextPath()+"/user/login");
    }
}

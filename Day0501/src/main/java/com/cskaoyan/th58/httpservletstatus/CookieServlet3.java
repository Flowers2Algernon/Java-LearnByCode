package com.cskaoyan.th58.httpservletstatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class CookieServlet3 extends HttpServlet {
    //this method is used to write a login web, when the user input the username and password,
    //refresh the web to another page, this page can show the different username individually
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //first we need to get the request resource through the following step
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("login".equals(replace)){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //here we write the login method
        //the logic is simple: according to the client's req information-username and password,
        //do some verify, if verified, refresh to another page
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //the following is do some verify work
        //...
        //refresh to another page
        resp.setHeader("refresh","5;url="+req.getContextPath()+"/user/info");
        //set a cookie
        Cookie cookie = new Cookie("username", username);
        resp.addCookie(cookie);
        resp.getWriter().println("login success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("info".equals(replace)){
            info(req,resp);
        }
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //use cookie to show the username
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())){
                    resp.getWriter().println("welcome "+cookie.getValue());
                }
            }
        }
    }
}

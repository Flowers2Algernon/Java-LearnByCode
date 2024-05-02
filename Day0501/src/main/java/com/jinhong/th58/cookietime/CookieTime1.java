package com.cskaoyan.th58.cookietime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/time1")
public class CookieTime1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set a survival time for cookie
        Cookie cookie = new Cookie("name", "jin");
        cookie.setMaxAge(180);//the unit is second
        resp.addCookie(cookie);
    }
}

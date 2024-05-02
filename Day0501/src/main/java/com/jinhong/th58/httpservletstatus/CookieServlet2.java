package com.cskaoyan.th58.httpservletstatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/cookie2")
public class CookieServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //this servlet is to show the last visit web time use cookie
        //to receive the clients cookie information
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Last")){
                    String value = cookie.getValue();
                    resp.getWriter().println(new Date(Long.parseLong(value)));
                }
            }
        }

        //cookie object's value can only accept String type, the other type is not accepted.
        //in value it cant have any space and other character
        long timeMillis = System.currentTimeMillis();
        Cookie cookie = new Cookie("Last", timeMillis + "");
        resp.addCookie(cookie);
    }
}

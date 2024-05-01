package com.cskaoyan.th58.cookietime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/time2")
public class CookieTime2 extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //delete the cookie-- just set the time to 0 is not enough,
        //because the cookie is stored in client, we set the time to 0 is occur in servlet
        //so we need to sync the servlet setting to client
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())){
                    //delete the cookie
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    System.out.println(cookie.getValue());//to observe the cookie is deleted?
                }
            }
        }
    }
}

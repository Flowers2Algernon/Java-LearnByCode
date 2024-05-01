package com.cskaoyan.th58.path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/path2")
public class CookiePath2 extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())){
                    System.out.println(cookie.getValue());
                    cookie.setMaxAge(0);
                    //when you delete the cookie, if you have set its path, now you must rewrite its set path to delete it
                    cookie.setPath(req.getContextPath()+"/a/");
                    resp.addCookie(cookie);
                }
            }
        }
    }
}

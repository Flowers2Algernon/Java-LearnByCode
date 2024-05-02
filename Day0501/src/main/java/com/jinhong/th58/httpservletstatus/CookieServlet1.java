package com.cskaoyan.th58.httpservletstatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie1")
public class CookieServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //to receive the client carries cookie
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName()+":"+cookie.getValue());
            }
        }


        //first, according to the ee standard, Cookie is a Class, have its own constructor
        //here we set a cookie Object,this cookie object will through the servlet pass to the client,
        //and will saved by the client, when the next time the client visit the servlet, the client will
        //carry the saved cookie object, so the servlet can determine which client is visited it.
        Cookie cookie = new Cookie("name", "Jin");
        //bring in set-cookie resp header
        resp.addCookie(cookie);
        //we can use packet grabber to perspective the Cookie object in resp header
    }
}

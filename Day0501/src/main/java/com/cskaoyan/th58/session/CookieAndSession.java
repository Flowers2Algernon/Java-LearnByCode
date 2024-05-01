package com.cskaoyan.th58.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/session3")
public class CookieAndSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())){
                    cookie.setMaxAge(60*60);
                    resp.addCookie(cookie);//将其从服务器端的更改保存到客户端
                }
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("password","123asd");
        Object password = session.getAttribute("password");
        System.out.println(password);
        System.out.println("========");
    }
}

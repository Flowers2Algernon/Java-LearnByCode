package com.cskaoyan.th58.httpservletstatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/statues1")
@MultipartConfig
public class HttpServletStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //print the client's ip
        String remoteAddr = req.getRemoteAddr();
        System.out.println("client's ip is"+remoteAddr);
        //print current req information
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        String protocol = req.getProtocol();
        System.out.println(requestURI+", "+method+". "+protocol);

        //print the represent head
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()){
            String headName = headers.nextElement();
            String headerValue = req.getHeader(headName);
            System.out.println(headName+headerValue);

        }
    }
}

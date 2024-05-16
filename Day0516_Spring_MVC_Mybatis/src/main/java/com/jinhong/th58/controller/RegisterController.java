package com.jinhong.th58.controller;

import com.jinhong.th58.dao.User;
import com.jinhong.th58.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.attribute.UserPrincipal;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping("/register")
    @ResponseBody
    public Object register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交的参数，存储到数据库中
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Part image = request.getPart("avatar");
        String imageSubmittedFileName = image.getSubmittedFileName();
        //将上述信息添加到数据库中
        User user = new User(null, username, password, imageSubmittedFileName);
        registerService.register(user);
        //注册页面跳转到登录页面
        response.setHeader("refresh","2;url=login.jsp");
        return "注册成功，即将跳转到用户登录界面";
    }
}

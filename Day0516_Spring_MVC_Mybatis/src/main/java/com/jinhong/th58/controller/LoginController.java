package com.jinhong.th58.controller;

import com.jinhong.th58.dao.User;
import com.jinhong.th58.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    //此处执行登录校验逻辑
    public Object login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        //数据库获取数据校验
        User databaseUser = loginService.login(username);
        if (databaseUser==null){
            return "用户名输入错误，无此用户";
        }
        if (databaseUser.getPassword()!=password){
            return "密码输入错误";
        }else {
            //将当前用户存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user",databaseUser);
            //跳转到个人信息展示页
            response.setHeader("refresh","2;url=http://localhost/display");
            return "登录成功，即将跳转到个人信息展示页";
        }
    }
}

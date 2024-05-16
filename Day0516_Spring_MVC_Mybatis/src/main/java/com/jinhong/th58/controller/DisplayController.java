package com.jinhong.th58.controller;

import com.jinhong.th58.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DisplayController {
    @RequestMapping("display")
    public Object display(HttpServletRequest request){
        //此处展示用户个人信息
        //先从session中读取user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }
}

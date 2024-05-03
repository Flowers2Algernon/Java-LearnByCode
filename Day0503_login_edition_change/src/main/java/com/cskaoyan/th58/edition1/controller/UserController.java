package com.cskaoyan.th58.edition1.controller;

import com.cskaoyan.th58.edition1.model.UserJsonUtil;
import com.cskaoyan.th58.edition0.bean.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//当要将下述使用json文件存储的代码改为使用数据库存储数据时，需要改动哪些代码？
@WebServlet("/user/*")
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先做请求的分发
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("login".equals(replace)){
            login(req,resp);
        }else if ("register".equals(replace)){
            register(req,resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //注册逻辑
        //接收用户提交过来的参数信息，进行校验，保障用户名的唯一性，存储到系统中，并给用户返回信息
        //此时使用的仍然是form表单，是key=value&key=value的类型
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (StringUtils.isEmpty(username)){
            resp.getWriter().println("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            resp.getWriter().println("密码不能为空");
        }
        if (StringUtils.isEmpty(confirmPassword)){
            resp.getWriter().println("确认密码不能为空");
        }
        if (!(password.equals(confirmPassword))){
            resp.getWriter().println("两次密码不一致");
            return;
        }
        //此时调用Model层的注册判断逻辑，根据返回的Integer，来显示不同的视图
        Integer code = UserJsonUtil.register(new User(username, password));
//        Integer code = UserDBUtil.register(new com.cskaoyan.th58.edition1.model.User(username,password));
        if (code==404){
            resp.getWriter().println("当前用户名已被组成");
        }else if (code==200){
            resp.getWriter().println("注册成功");
        }else {
            resp.getWriter().println("服务器繁忙");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

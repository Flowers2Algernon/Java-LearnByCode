package com.cskaoyan.th58.edition2.controller;

import com.cskaoyan.th58.edition2.mapper.UserJsonMapper;
import com.cskaoyan.th58.edition2.mapper.UserMapper;
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
    //父类引用指向子类实现
    UserMapper userMapper = new UserJsonMapper();//使用Json文件存储时，就使用该实现
//    UserMapper userMapper = new UserDBMapper();//使用database存储数据时，就使用该实现‘
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
        //注意此处改成了成员变量的userMapper,而不是版本一中的UserJsonMapper实现
        Integer code = userMapper.register(new User(username, password));
        //相较于版本一中的如下实现，此处即使后续改为了数据库或者Json,此处仍然不用动
        //        Integer code = UserDBModel_deprecate.register(new User(username, password));
//        Integer code = UserJsonModel.register(new User(username, password));
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

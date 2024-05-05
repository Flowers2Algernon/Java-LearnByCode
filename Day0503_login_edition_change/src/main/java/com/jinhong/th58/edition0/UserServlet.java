package com.cskaoyan.th58.edition0;

import com.cskaoyan.th58.edition0.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


//当要将下述使用json文件存储的代码改为使用数据库存储数据时，需要改动哪些代码？
//@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
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

        //此时使用的是json来存放的数据
        //首先需要确认用户名是否在json中，如果在其中，则提示用户名已存在，如果不在其中，注册
        //现在需要做的事情是去读取class path目录下的该文件，读取其中的数据
        String path = UserServlet.class.getClassLoader().getResource("user.json").getPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String string = bufferedReader.readLine();
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = new ArrayList<>();
        //考虑文件为空的情况
        if (!StringUtils.isEmpty(string)){
            //不为空，说明没有人注册过
            //构建一个json的复杂类型
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, User.class);
            users = objectMapper.readValue(string, collectionType);
            for (User user : users) {
                if (username.equals(user.getUsername())){
                    //此时view和model的代码是紧密耦合在一起的，一个变动，必然会对另一个产生影响
                    resp.getWriter().println("当前用户名已注册");//此展示代码当切换为数据库时不用动。但是其上方的代码均需要改动
                    return;//此时代码是紧耦合的，当需要改动时，维护成本非常高
                }
            }
            //空，未被注册的情况
            users.add(new User(username, password));
            //最后，将user全部写入到json文件中
            FileWriter fileWriter = new FileWriter(path);
            //将user对象以json字符串的形式来写入json文件中
            fileWriter.write(objectMapper.writeValueAsString(users));
            fileWriter.flush();
            fileWriter.close();
            resp.getWriter().println("注册成功");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

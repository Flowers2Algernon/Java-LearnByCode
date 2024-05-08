package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.service.MarketAdminService;
import com.cskaoyan.market.service.impl.MarketAdminServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet("/admin/auth/*")
public class AdminAuthController extends HttpServlet {

    private MarketAdminService adminService = new MarketAdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/auth/", "");
        if("login".equals(replace)){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //在login方法下，主要的业务逻辑如下：
        //首先获取位于请求体中的json字符串
        //获取json字符串中的用户名、密码，进行常规的校验、判空处理
        //调用业务层代码，进行登录校验，校验用户名、密码是否正确，如果不匹配直接返回结果
        //如果校验通过，则需要修改一次Market_admin表，需要更新最新的ip登录地址，登录时间
        //返回结果--返回跟公网相同的返回值即可
        //请求体中的请求类型是{"errno":605,"errmsg":"用户帐号或密码不正确"}
        //不是key=value&key=value类型，无法使用getparameter
        String requestBody = req.getReader().readLine();
        //得到请求体
        System.out.println(requestBody);
        //接下来可以使用objectMapper.readValue使用一个对象来接收，就可以完成数据的封装工作
        //但是使用上述的方法需要创建一个admin类，当json里面的属性比较少时，此时再构建一个类就不是很划算
        //建议直接使用下面的方法来获取json字符串里面的数据
        String username = JacksonUtil.parseString(requestBody, "username");
        String password = JacksonUtil.parseString(requestBody, "password");

        //做一些数据校验工作
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            Object fail = ResponseUtil.fail(404, "username or password can't be empty");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);

            return;
        }
        MarketAdmin marketAdmin = adminService.login(username,password);
        if(marketAdmin==null){
            //用户名、密码错误{"errno":605,"errmsg":"用户帐号或密码不正确"}
            //此处的状态码可以自定义，前端只会判断是否为0，如果不为0，便是错误。
            //具体的数字只是为了调用者可以更方便的排查故障
            Object fail = ResponseUtil.fail(605, "用户帐号或密码不正确");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);
            return;
        }
        //用户名、密码匹配，做到和公网相同的返回结果
        //{
        //	"errno": 0,
        //	"data": {
        //		"adminInfo": {
        //			"nickName": "admin123",
        //			"avatar": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
        //		},
        //		"token": "e9ff69ef-fc99-4081-be8f-76ca3593422b"
        //	},
        //	"errmsg": "成功"
        //}

        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, String> adminInfo = new HashMap<>();
        adminInfo.put("nickName",marketAdmin.getUsername());
        adminInfo.put("avatar",marketAdmin.getAvatar());
        data.put("adminInfo",adminInfo);
        //token其实就是session域的编号
        HttpSession session = req.getSession();
        session.setAttribute("admin",marketAdmin);
        data.put("token",session.getId());
        Object ok = ResponseUtil.ok(data);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/auth/", "");
        if("info".equals(replace)){
            info(req,resp);
        }
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        MarketAdmin marketAdmin = (MarketAdmin) session.getAttribute("admin");
        HashMap<String, Object> data = new HashMap<>();
        //roles 和 perms是权限相关的，此项目直接写死，
        data.put("roles", Arrays.asList("超级管理员"));
        data.put("name",marketAdmin.getUsername());
        data.put("perms",Arrays.asList("*"));
        data.put("avatar",marketAdmin.getAvatar());
        Object ok = ResponseUtil.ok(data);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }
}

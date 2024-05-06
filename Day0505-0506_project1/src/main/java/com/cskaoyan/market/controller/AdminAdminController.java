package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.db.domain.MarketAdminListVo;
import com.cskaoyan.market.service.*;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/admin/*")
public class AdminAdminController extends HttpServlet {
    private AdminAdminListService adminAdminListService = new AdminAdminListServiceImpl1();
    private AdminAdminCreateService adminAdminCreateService = new AdminAdminCreateServiceImpl1();
    private AdminAdminUpdateService adminAdminUpdateService = new AdminAdminUpdateServiceImpl1();
    private AdminAdminDeleteService adminAdminDeleteService = new AdminAdminDeleteServiceImpl1();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/admin/", "");
        if ("list".equals(replace)){
            list(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/admin/", "");
        if ("create".equals(replace)){
            create(req,resp);
        }else if ("update".equals(replace)){
            update(req,resp);
        } else if ("delete".equals(replace)) {
            delete(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().readLine();
        Integer id = JacksonUtil.parseInteger(requestBody, "id");
        Integer deleted = adminAdminDeleteService.delete(id);
        if (deleted == 1){
            Object ok = ResponseUtil.ok();
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().readLine();
        String username = JacksonUtil.parseString(requestBody,"username");
        String password = JacksonUtil.parseString(requestBody, "password");
        String avatar = JacksonUtil.parseString(requestBody, "avatar");
        String addTime = JacksonUtil.parseString(requestBody, "addTime");
        Integer[] roleIds = new Integer[]{JacksonUtil.parseInteger(requestBody, "roleIds")};
        Integer id = JacksonUtil.parseInteger(requestBody,"id");
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            Object fail = ResponseUtil.fail(404, "用户名或密码不能为空");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);
            return;
        }
        if (!username.matches("^[a-zA-Z].*")){
            Object fail = ResponseUtil.fail(408, "用户名不符合规范");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime addTimeLocal = LocalDateTime.parse(addTime, dateTimeFormatter);
        MarketAdmin marketAdmin = adminAdminUpdateService.update(username, avatar, password, id, roleIds, now, addTimeLocal);
        Object ok = ResponseUtil.ok(marketAdmin);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //create方法无参数
        //创建时是这种样式，Json，无法使用req.getparameter来获取参数
//        {"username":"aabbcc","password":"admin123","roleIds":[10]}
        //不是key=value&key=value形式的可以使用如下方法来读取
        //req.getInputStream或者
        String requestBody = req.getReader().readLine();
        //当json内部的对象非常少时，额外构建一个类来封装里面的数据不划算，可以使用如下简便方式
        String username = JacksonUtil.parseString(requestBody, "username");
        String password = JacksonUtil.parseString(requestBody, "password");
        String avatar = JacksonUtil.parseString(requestBody, "avatar");
        Integer[] roleIds = new Integer[]{JacksonUtil.parseInteger(requestBody, "roleIds")};
        //做一些校验工作
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            Object fail = ResponseUtil.fail(404, "用户名或密码不能为空");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);
            return;
        }
        if (!username.matches("^[a-zA-Z].*")){
            Object fail = ResponseUtil.fail(408, "用户名不符合规范");
            String jsonStr = JacksonUtil.getObjectMapper().writeValueAsString(fail);
            resp.getWriter().println(jsonStr);
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        MarketAdmin marketAdmin = adminAdminCreateService.create(username, avatar, password, null, roleIds, now, now);
        Object ok = ResponseUtil.ok(marketAdmin);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此list方法中需要将请求参数获取到之后将其传递给相应的service层实现来获取对象
        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        String username = req.getParameter("username");
        Integer page =null;
        Integer limit = null;
        try {
            if (pageParam!=null){
                page = Integer.parseInt(pageParam);
            }
            if (limitParam!=null){
                limit = Integer.parseInt(limitParam);
            }
        }catch (Exception e){
            //此时有非法输入
            Object o = ResponseUtil.badArgumentValue();
            String s = JacksonUtil.getObjectMapper().writeValueAsString(o);
            resp.getWriter().println(s);
        }
        List<MarketAdminListVo> list = adminAdminListService.list(limit, page, username, sort, order);
        Object o = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

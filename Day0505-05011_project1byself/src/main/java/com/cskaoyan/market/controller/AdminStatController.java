package com.cskaoyan.market.controller;

import com.cskaoyan.market.service.AdminStatService;
import com.cskaoyan.market.service.AdminStatServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import com.cskaoyan.market.vo.AdminStatUserBo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/admin/stat/*")
public class AdminStatController extends HttpServlet {
    private AdminStatService adminStatService = new AdminStatServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/stat/", "");
        if ("user".equals(replace)){
            user(req,resp);
        }
    }

    private void user(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //直接查询数据库中数据即可
        List<String> strings = new ArrayList<>();
        strings.add("day");
        strings.add("users");
        //请求报文中无内容，上述内容是固定的
        List<AdminStatUserBo> list = adminStatService.getDayAndUserCount();
        List<Object> resultList = new ArrayList<>();
        resultList.add(strings);
        resultList.add(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.okList(resultList)));
    }
}

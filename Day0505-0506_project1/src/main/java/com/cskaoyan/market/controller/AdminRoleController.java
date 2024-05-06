package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketRoleLabelOptions;
import com.cskaoyan.market.service.AdminRoleOptionsService;
import com.cskaoyan.market.service.AdminRoleOptionsServiceImpl1;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/role/*")
public class AdminRoleController extends HttpServlet {
    private AdminRoleOptionsService adminRoleOptionsService = new AdminRoleOptionsServiceImpl1();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/role/", "");
        if ("options".equals(replace)){
            options(req,resp);
        }
    }

    private void options(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //请求体中什么也没有，生成一个value和label的list当作data返回即可
        List<MarketRoleLabelOptions> list = adminRoleOptionsService.options();
        Object o = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

package com.cskaoyan.market.controller;

import com.cskaoyan.market.bo.AdminConfigMallBo;
import com.cskaoyan.market.db.domain.MarketSystem;
import com.cskaoyan.market.service.AdminConfigService;
import com.cskaoyan.market.service.AdminConfigServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/admin/config/*")
public class AdminConfigController extends HttpServlet {
    private AdminConfigService adminConfigService = new AdminConfigServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/config/", "");
        if ("mall".equals(replace)) {
            mallGet(req, resp);
        }
    }

    private void mallGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //返回一个map,里面key是名称，value是具体的字段，将数据存储到market_system表里面
        //get里面没有发送任何数据

        //返回结果
        ArrayList<String> list = new ArrayList<>();
        list.add("market_mall_address");
        list.add("market_mall_latitude");
        list.add("market_mall_longitude");
        list.add("market_mall_name");
        list.add("market_mall_phone");
        list.add("market_mall_qq");
        //写入到数据库
        //从数据库中读取该map
        HashMap<String, String> map = adminConfigService.mallGet(list);
        Object ok = ResponseUtil.ok(map);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/config/", "");
        if ("mall".equals(replace)) {
            mallPost(req, resp);
        }
    }

    private void mallPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().readLine();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();
        String market_mall_address = JacksonUtil.parseString(requestBody, "market_mall_address");
        String market_mall_latitude = JacksonUtil.parseString(requestBody, "market_mall_latitude");
        String market_mall_longitude = JacksonUtil.parseString(requestBody, "market_mall_longitude");
        String market_mall_name = JacksonUtil.parseString(requestBody, "market_mall_name");
        String market_mall_phone = JacksonUtil.parseString(requestBody, "market_mall_phone");
        String market_mall_qq = JacksonUtil.parseString(requestBody, "market_mall_qq");
        //以上读取完成输入数据
        HashMap<String, String> map = new HashMap<>();
        map.put("market_mall_address", market_mall_address);
        map.put("market_mall_latitude", market_mall_latitude);
        map.put("market_mall_longitude", market_mall_longitude);
        map.put("market_mall_name", market_mall_name);
        map.put("market_mall_phone", market_mall_phone);
        map.put("market_mall_qq", market_mall_qq);
        adminConfigService.mallPost(map);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok()));
    }
}

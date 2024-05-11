package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketCoupon;
import com.cskaoyan.market.service.AdminCouponService;
import com.cskaoyan.market.service.AdminCouponServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/coupon/*")
public class AdminCouponController extends HttpServlet {
    private AdminCouponService adminCouponService = new AdminCouponServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/coupon/", "");
        if ("list".equals(replace)){
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        String typeParam = req.getParameter("type");
        String statusParam = req.getParameter("status");
        String name = req.getParameter("name");
        Integer page = null;
        Integer limit = null;
        Short type = null;
        Short status = null;
        try {
            if (!StringUtils.isEmpty(typeParam)){
                type = Short.valueOf(typeParam);
            }
            if (!StringUtils.isEmpty(statusParam)){
                status = Short.valueOf(statusParam);
            }
            page = Integer.valueOf(pageParam);
            limit = Integer.valueOf(limitParam);
        }catch (Exception e ){
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.badArgument()));
            return;
        }
       List<MarketCoupon> list = adminCouponService.list(page,limit,sort,order,type,status,name);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.okList(list)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/coupon/", "");
        if ("create".equals(replace)){
            create(req,resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //非Key=value类型
        String requestBody = req.getReader().readLine();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();
        MarketCoupon marketCoupon = objectMapper.readValue(requestBody, MarketCoupon.class);
        adminCouponService.create(marketCoupon);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok(marketCoupon)));
    }
}

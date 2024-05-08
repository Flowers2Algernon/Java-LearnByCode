package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketOrder;
import com.cskaoyan.market.service.AdminOrderService;
import com.cskaoyan.market.service.AdminOrderServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@WebServlet("/admin/order/*")
public class AdminOrderController extends HttpServlet {
    private AdminOrderService adminOrderService = new AdminOrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/order/", "");
        if ("list".equals(replace)){
            list(req,resp);
        } else if ("detail".equals(replace)) {
            detail(req,resp);
        }
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(idParam);
        }catch (Exception e ){
            resp.setStatus(404);
            return;
        }
        HashMap<String, Object> map =  adminOrderService.details(id);
        Object o = ResponseUtil.ok(map);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //key=value&key=value形式
        String limitParam = req.getParameter("limit");
        String pageParam = req.getParameter("page");
        String orderStatus = req.getParameter("orderStatusArray");//订单状态
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");//次序
        String orderIdParam = req.getParameter("orderId");
        String startParam = (req.getParameter("start"));
        String endParam = (req.getParameter("end"));
        String orderSn = req.getParameter("orderSn");
        String userIdParam = req.getParameter("userId");
        Integer orderId = null;
        Integer userId = null;
        Integer page = null;
        Integer limit = null;
        LocalDateTime start = null;
        LocalDateTime end = null;
        try {
//            start = LocalDateTime.parse(startParam);
//            end = LocalDateTime.parse(endParam);
            page = Integer.parseInt(pageParam);
            limit = Integer.parseInt(limitParam);
//            userId = Integer.parseInt(userIdParam);
        }catch (Exception e){
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.badArgument()));
            System.out.println("参数转换错误");
            return;
        }
        //调用service层来获取list数据
      List<MarketOrder> list = adminOrderService.list(page,limit,orderStatus,sort,order,start,end,orderSn,orderId,userId);
        Object o = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

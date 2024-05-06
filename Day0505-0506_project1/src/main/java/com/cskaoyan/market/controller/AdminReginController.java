package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketRegionVo;
import com.cskaoyan.market.service.MarketRegionService;
import com.cskaoyan.market.service.MarketRegionServiceImpl1;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/region/*",loadOnStartup = 1)
public class AdminReginController extends HttpServlet {
    private MarketRegionService regionService = new MarketRegionServiceImpl1();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先分发请求
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/region/", "");
        if ("list".equals(replace)){
            //进入list方法
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处返回的是省市区嵌套的list
        //因为此处省市数量变化可能性不大，在查询一次之后，将其放入内存中，
        //先尝试从context域中获取，如果获取不到，再进行数据库查询
        ServletContext servletContext = getServletContext();
        List<MarketRegionVo> marketRegionVoList = (List<MarketRegionVo>) servletContext.getAttribute("region");
        if (marketRegionVoList==null){
            //先get后set
            marketRegionVoList = regionService.list();
            servletContext.setAttribute("region",marketRegionVoList);
        }
        Object o = ResponseUtil.okList(marketRegionVoList);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

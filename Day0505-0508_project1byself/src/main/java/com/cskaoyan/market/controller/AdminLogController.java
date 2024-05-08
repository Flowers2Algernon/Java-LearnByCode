package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketLog;
import com.cskaoyan.market.service.AdminLogService;
import com.cskaoyan.market.service.impl.AdminLogServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/log/*")
public class AdminLogController extends HttpServlet {
    private AdminLogService adminLogService = new AdminLogServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/log/", "");
        if ("list".equals(replace)){
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        String username = req.getParameter("name");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        Integer limit = null;
        Integer page = null;
        try {
            limit = Integer.parseInt(limitParam);
            page= Integer.parseInt(pageParam);
        }catch (Exception e){
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.badArgument()));
        }
        List<MarketLog> list =adminLogService.list(page, limit, username, sort,order);
        Object o = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

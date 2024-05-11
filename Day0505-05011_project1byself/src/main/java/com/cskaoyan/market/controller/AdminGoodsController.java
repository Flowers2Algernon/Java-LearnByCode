package com.cskaoyan.market.controller;

import com.cskaoyan.market.bo.AddGoodsBo;
import com.cskaoyan.market.db.domain.MarketGoods;
import com.cskaoyan.market.service.MarketGoodsService;
import com.cskaoyan.market.service.impl.MarketGoodsServiceImpl1;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/goods/*")
public class AdminGoodsController extends HttpServlet {
    private MarketGoodsService goodsService = new MarketGoodsServiceImpl1();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/goods/", "");
        if ("create".equals(replace)){
            create(req,resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //{"goods":{"picUrl":"http://39.101.189.16:8083/wx/storage/fetch/1rttekmd98pewda11tc2.jpg","gallery":["http://39.101.189.16:8083/wx/storage/fetch/q4cevjhoasyxutn8vgwv.jpg
        //抓包得到的是如上字符串，无法使用这类形式来获取其数据 String username = JacksonUtil.parseString(requestBody,"username");
        //采用如下方式来获取用户输入的数据：
        String requestBody = req.getReader().readLine();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();
        AddGoodsBo addGoodsBo = objectMapper.readValue(requestBody, AddGoodsBo.class);
        goodsService.create(addGoodsBo.getGoods(),addGoodsBo.getSpecifications(),addGoodsBo.getAttributes(),addGoodsBo.getProducts());
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/goods/", "");
        if ("list".equals(replace)){
            list(req,resp);
        } else if ("catAndBrand".equals(replace)) {
            catAndBrand(req,resp);
        }
    }

    private void catAndBrand(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处需要返回的data数据是一个List，
        //该list由两个子list组成，一个是brandList,一个是categoryList
        //调用service层方法直接返回即可
        Map<String, Object> map = goodsService.catAndBrand();
        Object ok = ResponseUtil.ok(map);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String limitParam = req.getParameter("limit");
        String pageParam = req.getParameter("page");
        String name = req.getParameter("name");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        String goodsIdParam = req.getParameter("goodsId");
        String goodsSnParam = req.getParameter("goodsSn");
        Integer limit = null;
        Integer page = null;
        try {
            limit = Integer.parseInt(limitParam);
            page = Integer.parseInt(pageParam);
        }catch (Exception e ){
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.badArgument()));
            return;
        }
        //此时需要调用service接口来获得全部数据
       List<MarketGoods> list =  goodsService.list(limit,page,goodsSnParam,goodsIdParam,order,sort,name);
        //将上述list当作data传入到response里面去
        Object o = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }
}

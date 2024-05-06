package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketUser;
import com.cskaoyan.market.service.MarketUserDetailSearchById;
import com.cskaoyan.market.service.MarketUserDetailSearchByIdImpl;
import com.cskaoyan.market.service.MarketUserService;
import com.cskaoyan.market.service.MarketUserServiceImpl;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user/*")
public class AdminUserController extends HttpServlet {

    private MarketUserService userService = new MarketUserServiceImpl();
    private MarketUserDetailSearchById detailSearchById = new MarketUserDetailSearchByIdImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此处分发请求list,根据request urL里面的链接内容来选择相应的字符
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/user/", "");
        if ("list".equals(replace)){
            list(req,resp);
        }else if ("detail".equals(replace)){
            detail(req,resp);
        }
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        Integer id = null;
        try {
            if (!StringUtils.isEmpty(idParam)){
                id = Integer.parseInt(idParam);
            }
        }catch (Exception e){
            //输入非法参数
            Object o = ResponseUtil.badArgumentValue();
            String s = JacksonUtil.getObjectMapper().writeValueAsString(o);
            resp.getWriter().println(s);
            return;
        }
        //将相应的id对应的用户显示出去
        MarketUser marketUser = detailSearchById.detail(id);
        Object ok = ResponseUtil.ok(marketUser);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //根据网页请求的信息http://39.101.189.16:8083/admin/user/list?page=1&limit=20&username=&mobile=&userId=&sort=add_time&order=desc
        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        String username = req.getParameter("username");
        String mobile = req.getParameter("mobile");
        String idParam = req.getParameter("userId");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        Integer page = null;
        Integer limit = null;
        Integer id = null;
        //考虑到用户可能输入的是错误的参数，无法进行类型转换
        //使用try-catch
        try {
            if (pageParam!=null){
                page = Integer.parseInt(pageParam);
            }
            if (limitParam!=null){
                limit = Integer.parseInt(limitParam);
            }
            if (idParam!=null){
                id = Integer.parseInt(idParam);
            }
        }catch (Exception e){
            Object o = ResponseUtil.badArgumentValue();
            String s = JacksonUtil.getObjectMapper().writeValueAsString(o);
            resp.getWriter().println(s);
            return;
        }
        //具体的业务处理逻辑
        List<MarketUser> list = userService.list(limit, page, username, mobile, id, sort, order);
        Object ok = ResponseUtil.okList(list);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ok));
    }
}

package com.jinhong.th58;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.MidiSystem;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)){
            resp.getWriter().println("参数不可以为空");
            return;
        }
        ArrayList<Product> products = (ArrayList<Product>) getServletContext().getAttribute("products");
        //以下是展示的演示
        for (Product product : products) {
            if (id.equals(product.getId())){
                resp.getWriter().println(product);
            }
        }
        String index = resp.encodeURL(req.getContextPath() + "/index");
        String addCart = resp.encodeURL(req.getContextPath() + "/addCart?id=" + id);
        String viewCart = resp.encodeURL(req.getContextPath() + "/viewCart");

        resp.getWriter().println("<div><a href='" + index + "'>返回首页</a></div>");
        resp.getWriter().println("<div><a href='" + addCart + "'>加入购物车</a></div>");
        resp.getWriter().println("<div><a href='" + viewCart + "'>查看购物车</a></div>");
    }
}

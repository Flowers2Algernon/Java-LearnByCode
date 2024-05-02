package com.jinhong.th58;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/index",loadOnStartup = 1)
public class HomeIndexDIsplay extends HttpServlet {
    //init方法会先执行
    @Override
    public void init() throws ServletException {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("1", "问界M5", "入门款智驾车"));
        products.add(new Product("2", "问界M7", "中级智驾车"));
        products.add(new Product("3", "小米su7", "明星流量车型"));
        products.add(new Product("4", "极氪001", "猎装车"));
        getServletContext().setAttribute("products",products);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此处显示init中的几个商品信息
        ArrayList<Product> products = (ArrayList<Product>) getServletContext().getAttribute("products");
        //遍历Product将其显示出来
        for (Product product : products) {
            String item = resp.encodeURL(req.getContextPath()+"/item?id="+product.getId());
            resp.getWriter().println("<div><a href='"+item+"'>"+product.getName()+"</a></div>");
        }
    }
}

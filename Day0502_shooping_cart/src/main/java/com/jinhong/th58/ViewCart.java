package com.jinhong.th58;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewCart")
public class ViewCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此处表示查看购物车
        HttpSession session = req.getSession();
        List<String> ids = (List<String>) session.getAttribute("ids");
        String index = resp.encodeURL(req.getContextPath() + "/index");
        if (ids==null){
            resp.setHeader("refresh","2;url="+index);
            return;
        }
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        for (String id : ids) {
            for (Product product : products) {
                if (id.equals(product.getId())){
                    resp.getWriter().println(product);
                }
            }
        }
    }
}

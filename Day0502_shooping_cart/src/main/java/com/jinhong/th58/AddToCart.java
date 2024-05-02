package com.jinhong.th58;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(StringUtils.isEmpty(id)){
            resp.getWriter().println("参数不可以为空");
            return;
        }
        //现在执行加入购物车操作
        HttpSession session = req.getSession();
        String index = resp.encodeURL(req.getContextPath() + "/index");
        //先get后set
//        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");
        List<String> ids = (List<String>) session.getAttribute("ids");
        if (ids==null){
            ids = new ArrayList<>();
        }
        ids.add(id);
        session.setAttribute("ids",ids);
        resp.setHeader("refresh","2;url="+index);
    }
}

package com.cskaoyan.th58.merge;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/pic/*")
public class Merge1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/pic/", "");
        if ("view".equals(replace)){
            view(req,resp);
        }
        if ("down".equals(replace)){
            down(req,resp);
        }
    }

    private void down(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Disposition","attachment;filename=1.jpg");
        view(req,resp);
    }

    private void view(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        //读取resource中的资源文件图片,通过classLoader.getResourceAsStream的方式
        ClassLoader classLoader = Merge1.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("1.jpg");
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,length);
        }
        inputStream.close();
        outputStream.close();
    }
}

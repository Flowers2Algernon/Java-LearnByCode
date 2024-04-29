package com.cskaoyan.th58.defaultSer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/")
public class DefaultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        //requestURI得到请求资源
        //以下需要解析请求资源
        System.out.println(requestURI.substring(5));
        //requestURI.substring(5)是去除应用名之后剩余请求的静态资源
        String requestStaticResource = requestURI.substring(5);
        //判断在resources目录下是否存在requestStaticResource该文件
        ClassLoader classLoader = DefaultServlet.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(requestStaticResource);
        if (inputStream!=null){
            //此时直接输出找到的文件
            ServletOutputStream outputStream = resp.getOutputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,length);
            }
            inputStream.close();
            outputStream.close();
            resp.setStatus(200);
        }else {
            resp.setStatus(404);
            resp.getWriter().println("can't find the file");
        }
    }

}

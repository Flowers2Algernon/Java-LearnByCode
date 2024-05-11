package com.cskaoyan.market.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/wx/storage/fetch/*")
//此处注意fetch后面需要加星号，不然永远无法匹配上
public class WxStorageFetchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //直接在此处去服务器盘符里面查找文件
        //如何查找？
        //将Key保留，其余部分全部替换为""，再将Key去盘符里面查找，查找到了就输出文件
        String requestURI = req.getRequestURI();
        String key = requestURI.replace(req.getContextPath() + "/wx/storage/fetch/", "");

        //todo 此处可以改为去配置文件中读取
        String basePath="E:\\wangdaodownload";
        File file = new File(basePath+"\\"+key);
        if (file.exists()&& file.isFile()){
            //输出文件
            //利用字节流，将该图片输出给客户端
            ServletOutputStream outputStream = resp.getOutputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while ((length=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,length);
            }
            return;
        }
        resp.setStatus(404);
    }
}

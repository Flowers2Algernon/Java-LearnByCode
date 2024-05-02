package com.cskaoyan.th58.upload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/upload1")
@MultipartConfig
public class Upload extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(111);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先要做的事情是获取位于req对象中的文件数据
        ServletInputStream inputStream = req.getInputStream();
//        System.out.println(111);
//        写入到硬盘上
        Part image = req.getPart("image");
        String realPath = getServletContext().getRealPath("image");
        String path = realPath+"/1.jpg";
        System.out.println(path);
        image.write(path);
//        File file = new File(path);
//        //如果父目录不存在，则创建
//        if (!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        int length = 0;
//        byte[] bytes = new byte[1024];
//        while ((length=inputStream.read(bytes))!=-1){
//            fileOutputStream.write(bytes,0,length);
//        }
//        fileOutputStream.close();
//
//        inputStream.close();
    }
}

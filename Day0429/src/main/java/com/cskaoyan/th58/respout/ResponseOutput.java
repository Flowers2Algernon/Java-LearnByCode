package com.cskaoyan.th58.respout;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/respout1")
public class ResponseOutput extends HttpServlet {
    //要将服务器中的某个文件传输给客户端，采用resp输出流
    //第一步，获取输出流
    //第二步，获取文件在硬盘上的路径,并用输入流读入
    //第三步，边读边输出到客户端/浏览器
    //第四步，关闭流

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        //获取文件硬盘路径
        //通过类加载中getResourceAsStream的方式
        ClassLoader classLoader = ResponseOutput.class.getClassLoader();
        //文件经过部署之后位于classes目录之下
        InputStream inputStream = classLoader.getResourceAsStream("1.jpg");
        //边读边写
        int length=0;
        byte[] bytes = new byte[1024];
        while ((length=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,length);
        }
        inputStream.close();
        outputStream.close();
    }
}

package file_upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/upload5")
@MultipartConfig
public class DiskFilePath extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("image");
        String fileName = part.getSubmittedFileName();
        String contentType = part.getContentType();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String dateNow = simpleDateFormat.format(date);
        String[] split = dateNow.split("-");
        String basePath = "image";
        String diskPath = "C:\\Users\\Jinhong\\Documents\\ESLPOD 6+3本教材\\1、Introduction to the United States\\PDFs";
        for (String s : split) {
            basePath = basePath + "/"+s;
        }
        String realPath = getServletContext().getRealPath(basePath);
        System.out.println(realPath);
        System.out.println("==============================");
        String path = realPath +"/" + fileName;//写入到应用根目录的path
        String diskRealPath = diskPath + "/" + basePath+"/"+fileName;
        File file1 = new File(diskRealPath);
        if (!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        part.write(diskRealPath);
    }
}

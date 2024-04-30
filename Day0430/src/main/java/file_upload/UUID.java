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

@WebServlet("/UUID")
@MultipartConfig
public class UUID extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("image");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String fileName = part.getSubmittedFileName();
        //change the filename
        String uuid = java.util.UUID.randomUUID().toString();
        fileName = uuid + fileName;
        System.out.println(fileName);
        //write the image to disk
        String contentType = part.getContentType();
        String realPath = getServletContext().getRealPath("image");
        String path = realPath +"/"+fileName;
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        part.write(path);
    }
}

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
import java.util.UUID;

@WebServlet("/upload3")
@MultipartConfig
public class HashFileName extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("image");
        String fileName = part.getSubmittedFileName();
        String contentType = part.getContentType();
        //intention to set a hashcode filename and deliver it to the disk
        UUID uuid = UUID.randomUUID();
        fileName = uuid + fileName;
        int hash = fileName.hashCode();
        //transfer the hash to 16th number
        String hexString = Integer.toHexString(hash);
        char[] charArray = hexString.toCharArray();
        String basePath = "image";
        for (char c : charArray) {
            basePath = basePath +"/" + c;
        }
        String realPath = getServletContext().getRealPath(basePath);
        String path = realPath + "/" + fileName;
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        part.write(path);
    }
}

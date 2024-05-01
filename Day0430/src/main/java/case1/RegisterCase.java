package case1;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@WebServlet("/user/*")
@MultipartConfig
public class RegisterCase extends HttpServlet {
    //this code is write to show a register-refresh-show process in web
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //divide the request--since there are many types of req, not just register
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("register".equals(replace)){
            register(req,resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //determine the username whether used
        ServletContext servletContext = getServletContext();
        Object username1 = servletContext.getAttribute("username");
        if (username1!=null){
            resp.getWriter().println(username+"is used by others, change another one");
            return;
        }

        //upload the image
        Part part = req.getPart("image");
        System.out.println(part);
        String fileName = part.getSubmittedFileName();
        String disk = "C:\\Users\\Jinhong\\Documents\\ESLPOD";//这里去添加到盘符的任意位置
        String diskPath = disk+"/"+fileName;
        File file = new File(diskPath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //上述代码是上传到磁盘中
        part.write(diskPath);

        User user = new User(username, password, diskPath);
        servletContext.setAttribute(username,user);

        resp.getWriter().println("注册成功，即将跳转到信息预览界面");
        resp.setHeader("refresh","5;url="+req.getContextPath()+"/user/info?username="+username);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //divide the req
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/user/", "");
        if ("info".equals(replace)){
            info(req,resp);
        }
    }
    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //the message in register need to share with info
        //how?
        //use servletContext
        String username = req.getParameter("username");
        ServletContext servletContext = getServletContext();
        User user =(User) servletContext.getAttribute(username);
        resp.getWriter().println("<div>"+user.getUsername()+"</div>");
//        resp.getWriter().println("<div>"+user.getPassword()+"<.div>");
        //get image
        //问题是如何显示本地文件的内容到服务器上
        resp.getWriter().println("<div><img src=\""+user.getDiskPath()+"\" ></div>");
    }
}

package com.cskaoyan.market.controller;

import com.cskaoyan.market.db.domain.MarketStorage;
import com.cskaoyan.market.service.AdminStorageService;
import com.cskaoyan.market.service.impl.AdminStorageServiceImpl1;
import com.cskaoyan.market.util.JacksonUtil;
import com.cskaoyan.market.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/admin/storage/*")
@MultipartConfig
//涉及图片上传等功能时，需要使用该注解来调用依赖
public class AdminStorageController extends HttpServlet {
    private AdminStorageService storageService = new AdminStorageServiceImpl1();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/storage/", "");
        if ("create".equals(replace)){
            create(req,resp);
        }else if ("update".equals(replace)){
            update(req,resp);
        } else if ("delete".equals(replace)) {
            delete(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().readLine();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();
        MarketStorage marketStorage = objectMapper.readValue(requestBody, MarketStorage.class);
        storageService.delete(marketStorage);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok()));
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处是更新对象名并返回更新
        //{"id":203,"key":"yo5w688nnrwfyq6lc3fn.jpg","name":"ku12221nknu.jpg","type":"image/jpeg","size":17026,"url":"http://39.101.189.16:8083/wx/storage/fetch/yo5w688nnrwfyq6lc3fn.jpg","addTime":"2024-05-08 11:25:54","updateTime":"2024-05-08 11:25:54","deleted":false}

        String requestBody = req.getReader().readLine();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();
        MarketStorage marketStorage = objectMapper.readValue(requestBody, MarketStorage.class);

//不是key=value&key=value类型，不能这么写
//        String name = req.getParameter("name");
//        Integer id = Integer.valueOf(req.getParameter("id"));
//        String key = req.getParameter("key");
//        String type = req.getParameter("type");
//        Integer size = Integer.valueOf(req.getParameter("size"));
//        String url = req.getParameter("url");
//        String addTime = req.getParameter("addTime");
//        MarketStorage marketStorage1 = new MarketStorage();
//        marketStorage1.setName(name);
//        marketStorage1.setType(type);
//        marketStorage1.setSize(size);
//        marketStorage1.setUrl(url);
//        marketStorage1.setKey(key);
//        marketStorage1.setUpdateTime(LocalDateTime.now());
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime addtimeLocal = LocalDateTime.parse(addTime, dateTimeFormatter);
//        marketStorage1.setAddTime(addtimeLocal);

        storageService.update(marketStorage);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok(marketStorage)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String replace = requestURI.replace(req.getContextPath() + "/admin/storage/", "");
        if ("list".equals(replace)){
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //此处是要返回查询的对象
        //key=value&key = value类型
        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        String key = req.getParameter("key");
        String name = req.getParameter("name");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        Integer page = null;
        Integer limit = null;
        try {
            page = Integer.parseInt(pageParam);
            limit = Integer.parseInt(limitParam);
        }catch (Exception e){
            resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.badArgument()));
            return;
        }
        List<MarketStorage> list =  storageService.list(limit, page,key,name,sort,order);
        List<MarketStorage> resultList = new ArrayList<>();
        for (MarketStorage marketStorage : list) {
            String url = marketStorage.getUrl();
            marketStorage.setUrl("http://localhost:8083"+url);
            resultList.add(marketStorage);
        }
        Object o = ResponseUtil.okList(resultList);
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(o));
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //此处实现图片上传功能
        //真正的图片上传就两个步骤
        //req.getPart(file)
        //part.write(相应的路径)--上传到服务器中的任意路径
        //但是上述两步只是上传，还没有将图片返回给相应的客户端请求者
        //所以还需要一个字节流的图片的写出过程
        Part part = req.getPart("file");
        //此处为什么断定是file,用网络抓包看Payload里面的file--2进制文件
        //接下来可以得到该file的一些属性
        String fileName = part.getSubmittedFileName();
        String contentType = part.getContentType();
        long size = part.getSize();
        //对其文件名进行更改，采用随即文件名
        String key = UUID.randomUUID()+"-"+fileName;
        //todo 此处改为使用配置文件读取
        String basePath = "E:\\wangdaodownload";//此处放置想将该图片保存在服务器中的哪个路径，可以是任意盘符
        part.write(basePath+"\\"+key);//注意此处basePath和key之间有一个\,否则图片无法正常输出
        //上述是将文件上传到盘符的任意位置
        //下述代码是将图片输出给客户端
        //首先需要将该文件上传记录上传到数据库中
        MarketStorage marketStorage = new MarketStorage();
        marketStorage.setDeleted(false);
        marketStorage.setAddTime(LocalDateTime.now());
        marketStorage.setUpdateTime(LocalDateTime.now());
        marketStorage.setSize((int)size);
        marketStorage.setName(fileName);
        marketStorage.setKey(key);
        //此处Key是最终的名字，fileName是上传的名字
        //如果将url前半部分按网页返回的内容设置为主机+端口号+路径+文件名
        //当后续主机或者端口号发生变化时，图片都会失效，所以此时不能这么存
        //数据库中只存端口号后半部分的位置+文件名，前半部分的位置需要输出的再拼接即可
        String relativePath = "/wx/storage/fetch/"+key;
        marketStorage.setUrl(relativePath);
        marketStorage.setType(contentType);
        //将上述参数都传入到数据库中去，使用service层的实现来处理
        storageService.create(marketStorage);

        //此处再对url进行处理，追加上主机和端口号
        //todo 此处可以写入配置文件中
        String url = marketStorage.getUrl();
        marketStorage.setUrl("http://localhost:8083"+url);
        //输出回复文件，注意此时图片上传后还是客户端还是无法看到文件
        resp.getWriter().println(JacksonUtil.getObjectMapper().writeValueAsString(ResponseUtil.ok(marketStorage)));
    }
}

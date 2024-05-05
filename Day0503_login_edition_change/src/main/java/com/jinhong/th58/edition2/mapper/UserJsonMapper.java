package com.cskaoyan.th58.edition2.mapper;

import com.cskaoyan.th58.edition1.controller.UserController;
import com.cskaoyan.th58.edition0.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserJsonMapper implements UserMapper{
    @Override
    public Integer register(User user) {
        try {
            //下述代码均为对数据的操作，将其封装为一个UserModel
            //此时使用的是json来存放的数据
            //首先需要确认用户名是否在json中，如果在其中，则提示用户名已存在，如果不在其中，注册
            //现在需要做的事情是去读取class path目录下的该文件，读取其中的数据
            String path = UserController.class.getClassLoader().getResource("user.json").getPath();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String string = bufferedReader.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = new ArrayList<>();
            //考虑文件为空的情况
            if (!StringUtils.isEmpty(string)) {
                //不为空，说明没有人注册过
                //构建一个json的复杂类型
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(List.class, com.cskaoyan.th58.edition0.bean.User.class);
                users = objectMapper.readValue(string, collectionType);
                for (User user1 : users) {
                    if (user.getUsername().equals(user1.getUsername())) {
                        //此时view和model的代码是紧密耦合在一起的，一个变动，必然会对另一个产生影响
//                    resp.getWriter().println("当前用户名已注册");
                        return 404;//返回状态码，减轻耦合
                    }
                }
                //空，未被注册的情况
                users.add(new com.cskaoyan.th58.edition0.bean.User(user.getUsername(), user.getPassword()));
                //最后，将user全部写入到json文件中
                FileWriter fileWriter = new FileWriter(path);
                //将user对象以json字符串的形式来写入json文件中
                fileWriter.write(objectMapper.writeValueAsString(users));
                fileWriter.flush();
                fileWriter.close();
                return 200;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 500;
    }
}

package com.jinhong.th58.service;

import com.jinhong.th58.domain.User;
import com.jinhong.th58.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:10
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserById(int id) {
        //先获取session   session.getMapper     session.commit()/close()
        //spring整合mybatis之后，会自动进行增强处理，我们无需编写上述代码逻辑
        return userMapper.selectOne(id);
    }

    @Override
    public void addUser(User user) {
        //关于事务的提交、关闭这些代码无需我们进行处理，会自动进行增强代理 AOP
        userMapper.insertOne(user);
    }
}

package com.cskaoyan.th58.edition3.service;

import com.cskaoyan.th58.edition1.model.User;
import com.cskaoyan.th58.edition3.mapper.UserJsonMapper;
import com.cskaoyan.th58.edition3.mapper.UserMapper;

public class UserServiceImpl2 implements UserService{
    UserMapper userMapper = new UserJsonMapper();
    @Override
    public Integer register(User user) {
//        return userMapper.register(user);
        return 0;
        }

    @Override
    public void function() {
        //        userMapper.delete();
//        userMapper.insert();
    }
}

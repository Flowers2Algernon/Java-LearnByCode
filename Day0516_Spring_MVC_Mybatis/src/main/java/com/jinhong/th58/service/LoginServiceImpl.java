package com.jinhong.th58.service;

import com.jinhong.th58.dao.User;
import com.jinhong.th58.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginMapper loginMapper;
    @Override
    public User login(String username) {
        User user = loginMapper.login(username);
        return user;
    }
}

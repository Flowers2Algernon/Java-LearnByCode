package com.jinhong.th58.service;

import com.jinhong.th58.domain.User;
import com.jinhong.th58.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.insertOne(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectOne(id);
    }
}

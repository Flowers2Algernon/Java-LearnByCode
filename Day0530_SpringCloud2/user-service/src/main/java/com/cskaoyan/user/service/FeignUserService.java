package com.cskaoyan.user.service;

import com.cskaoyan.th58.model.User;
import com.cskaoyan.user.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignUserService {
    @Autowired
    UserMapper userMapper;
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }
}

package com.jinhong.th58.service;

import com.jinhong.th58.dao.User;
import com.jinhong.th58.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    RegisterMapper registerMapper;

    @Override
    public void register(User user) {
        registerMapper.register(user);
    }
}

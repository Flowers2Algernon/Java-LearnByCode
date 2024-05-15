package com.jinhong.th58.service;

import com.jinhong.th58.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTransferServiceImpl implements UserTransferService{
    @Autowired
    UserMapper userMapper;

    @Override
    public double selectMoneyById(Integer id) {
        double v = userMapper.selectMoneyById(id);
        return v;
    }

    @Override
    public void updateMoneyByIdAndAmount(Integer id, double money) {
        userMapper.updateMoneyByIdAndAmount(id,money);
    }
}

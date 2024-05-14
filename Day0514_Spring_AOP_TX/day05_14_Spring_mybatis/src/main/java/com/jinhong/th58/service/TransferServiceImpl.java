package com.jinhong.th58.service;

import com.jinhong.th58.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:24
 * @Version 1.0
 */
@Service
public class TransferServiceImpl implements TransferService{

    //因为之前已经做了配置，已经将创建好的mapper实例放入到spring容器中了，所以此处可以直接从容器中取出
    @Autowired
    AccountMapper accountMapper;

    @Override
    public void transfer(String from, String to, Double money) {
        accountMapper.updateMoneyByName(from, money);

        //如果转账过程出现异常
        int i = 1 / 0;

        accountMapper.updateMoneyByName(to, -money);
    }
}

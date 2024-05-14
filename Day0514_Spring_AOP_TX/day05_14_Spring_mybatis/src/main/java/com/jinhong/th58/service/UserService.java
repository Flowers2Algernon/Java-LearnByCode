package com.jinhong.th58.service;

import com.cskaoyan.th58.domain.User;
import com.jinhong.th58.domain.User;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:10
 * @Version 1.0
 */
public interface UserService {

    User getUserById(int id);

    void addUser(User user);

}


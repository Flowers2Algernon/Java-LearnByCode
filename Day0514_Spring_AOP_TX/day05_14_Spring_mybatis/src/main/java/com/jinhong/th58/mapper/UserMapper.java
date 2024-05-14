package com.jinhong.th58.mapper;


import com.jinhong.th58.domain.User;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:07
 * @Version 1.0
 */
public interface UserMapper {

    User selectOne(Integer id);

    void insertOne(User user);
}

package com.jinhong.th58.mapper;

import com.jinhong.th58.domain.User;

public interface UserMapper {
    void insertOne(User user);

    User selectOne(Integer id);
}

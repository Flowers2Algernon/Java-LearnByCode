package com.jinhong.th58.service;

import com.jinhong.th58.domain.User;

public interface UserService {
    void addUser(User user);
    User getUserById(Integer id);
}

package com.cskaoyan.user.mapper;

import com.cskaoyan.user.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findById(@Param("id") Long id);
}

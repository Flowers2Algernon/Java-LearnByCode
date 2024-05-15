package com.jinhong.th58.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


public interface UserMapper {
    double selectMoneyById(Integer id);
    void updateMoneyByIdAndAmount(@Param("id") Integer id,@Param("money") double money);
}

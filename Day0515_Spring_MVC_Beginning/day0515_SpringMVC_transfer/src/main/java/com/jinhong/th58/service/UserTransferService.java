package com.jinhong.th58.service;

import org.apache.ibatis.annotations.Param;

public interface UserTransferService {
    double selectMoneyById(Integer id);
    void updateMoneyByIdAndAmount(@Param("id") Integer id, @Param("money") double money);
}

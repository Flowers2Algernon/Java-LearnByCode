package com.jinhong.th58.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:22
 * @Version 1.0
 */
public interface AccountMapper {

    void updateMoneyByName(@Param("name") String name,@Param("money") Double money);
}

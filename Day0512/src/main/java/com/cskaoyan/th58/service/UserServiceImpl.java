package com.cskaoyan.th58.service;

import com.cskaoyan.th58.mapper.UserMapper;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/11 10:26
 * @Version 1.0
 */
public class UserServiceImpl implements UserService{

    //希望从Spring容器中奖当前service实现类取出来的时候，mapper是有值的，不是null
    UserMapper userMapper;

    //如果希望spring帮助我们进行注入，那么需要提供一个set方法，暂时需要，后续学完注解之后就不再需要了
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    //如果希望Spring帮我们注入，那么需要提供一个set方法-该se方法只是暂时需要，后续学完注解之后就不需要了
//    public void setUserMapper(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }
}

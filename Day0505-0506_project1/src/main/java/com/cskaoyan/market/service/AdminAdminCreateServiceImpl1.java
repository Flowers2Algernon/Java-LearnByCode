package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.db.domain.MarketAdminExample;
import com.cskaoyan.market.db.mapper.MarketAdminMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;

public class AdminAdminCreateServiceImpl1 implements AdminAdminCreateService {
    @Override
    public MarketAdmin create(String username, String avatar, String password, Integer id, Integer[] roleIds, LocalDateTime updateTime, LocalDateTime addTime) {
        //此处为创建管理员的具体逻辑
        SqlSession session = MybatisUtil.getSession();
        MarketAdminMapper mapper = session.getMapper(MarketAdminMapper.class);
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdmin marketAdmin = new MarketAdmin();
        marketAdmin.setId(id);
        marketAdmin.setAvatar(avatar);
        marketAdmin.setPassword(password);
        marketAdmin.setUsername(username);
        marketAdmin.setAddTime(addTime);
        marketAdmin.setUpdateTime(updateTime);
        marketAdmin.setRoleIds(roleIds);
        mapper.insert(marketAdmin);
        session.commit();
        session.close();
        return marketAdmin;
    }
}

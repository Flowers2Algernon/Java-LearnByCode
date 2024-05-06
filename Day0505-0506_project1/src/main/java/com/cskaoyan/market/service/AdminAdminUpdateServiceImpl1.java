package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.db.domain.MarketAdminExample;
import com.cskaoyan.market.db.mapper.MarketAdminMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;

public class AdminAdminUpdateServiceImpl1 implements AdminAdminUpdateService {
    @Override
    public MarketAdmin update(String username, String avatar, String password, Integer id, Integer[] roleIds, LocalDateTime updateTime, LocalDateTime addTime) {
        SqlSession session = MybatisUtil.getSession();
        MarketAdminMapper mapper = session.getMapper(MarketAdminMapper.class);
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdmin marketAdmin = new MarketAdmin();
        marketAdmin.setUsername(username);
        marketAdmin.setUpdateTime(updateTime);
        marketAdmin.setRoleIds(roleIds);
        marketAdmin.setId(id);
        marketAdmin.setAddTime(addTime);
        marketAdmin.setPassword(password);
        marketAdmin.setAvatar(avatar);
        mapper.updateByExample(marketAdmin,marketAdminExample);
        session.commit();
        session.close();
        return marketAdmin;
    }
}

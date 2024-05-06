package com.cskaoyan.market.service;

import com.cskaoyan.market.db.mapper.MarketAdminMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AdminAdminDeleteServiceImpl1 implements AdminAdminDeleteService {
    @Override
    public Integer delete(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        MarketAdminMapper mapper = session.getMapper(MarketAdminMapper.class);
        return  mapper.deleteByPrimaryKey(id);
    }
}

package com.cskaoyan.market.service.impl;

import com.cskaoyan.market.db.domain.MarketUser;
import com.cskaoyan.market.db.mapper.MarketUserMapper;
import com.cskaoyan.market.service.MarketUserDetailSearchById;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class MarketUserDetailSearchByIdImpl implements MarketUserDetailSearchById {
    @Override
    public MarketUser detail(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        MarketUserMapper mapper = session.getMapper(MarketUserMapper.class);
        MarketUser marketUser = mapper.selectByPrimaryKey(id);
        session.commit();
        session.close();
        return marketUser;
    }
}

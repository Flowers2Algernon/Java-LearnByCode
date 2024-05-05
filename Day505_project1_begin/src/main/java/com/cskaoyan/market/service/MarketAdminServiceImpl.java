package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketAdmin;
import com.cskaoyan.market.db.domain.MarketAdminExample;
import com.cskaoyan.market.db.mapper.MarketAdminMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class MarketAdminServiceImpl implements MarketAdminService {
    @Override
    public MarketAdmin login(String username, String password) {
        SqlSession session = MybatisUtil.getSession();
        MarketAdminMapper marketAdminMapper = session.getMapper(MarketAdminMapper.class);
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria criteria = marketAdminExample.createCriteria();

        criteria.addUsernameEqualTo(username);
        criteria.addPasswordEqualTo(password);
        MarketAdmin marketAdmin = marketAdminMapper.selectOneByExample(marketAdminExample);
        session.commit();
        session.close();
        return marketAdmin;
    }
}

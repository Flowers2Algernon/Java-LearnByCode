package com.cskaoyan.market.service.impl;

import com.cskaoyan.market.db.domain.MarketLog;
import com.cskaoyan.market.db.domain.MarketLogExample;
import com.cskaoyan.market.db.mapper.MarketLogMapper;
import com.cskaoyan.market.service.AdminLogService;
import com.cskaoyan.market.util.MybatisUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminLogServiceImpl implements AdminLogService {
    @Override
    public List<MarketLog> list(Integer page, Integer limit, String username, String sort, String order) {
        SqlSession session = MybatisUtil.getSession();
        MarketLogMapper marketLogMapper = session.getMapper(MarketLogMapper.class);
        MarketLogExample marketLogExample = new MarketLogExample();
        MarketLogExample.Criteria criteria = marketLogExample.createCriteria();
        marketLogExample.setOrderByClause(sort+" "+order);
        if (!StringUtils.isEmpty(username)){
            criteria.andAdminLike("%"+username+"%");
        }
        PageHelper.startPage(page,limit);
        List<MarketLog> list = marketLogMapper.selectByExample(marketLogExample);
        return list;

    }
}

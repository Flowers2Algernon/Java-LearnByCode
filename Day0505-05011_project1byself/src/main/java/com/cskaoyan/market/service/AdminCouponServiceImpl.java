package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketCoupon;
import com.cskaoyan.market.db.domain.MarketCouponExample;
import com.cskaoyan.market.db.mapper.MarketCouponMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminCouponServiceImpl implements AdminCouponService {
    @Override
    public List<MarketCoupon> list(Integer page, Integer limit, String sort, String order, Short type, Short status, String name) {
        SqlSession session = MybatisUtil.getSession();
        MarketCouponMapper mapper = session.getMapper(MarketCouponMapper.class);
        MarketCouponExample marketCouponExample = new MarketCouponExample();
        MarketCouponExample.Criteria criteria = marketCouponExample.createCriteria();
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        if (status!=null){
            criteria.andStatusEqualTo(status);
        }
        List<MarketCoupon> list = mapper.selectByExample(marketCouponExample);
        session.commit();
        session.close();
        return list;
    }

    @Override
    public void create(MarketCoupon marketCoupon) {
        SqlSession session = MybatisUtil.getSession();
        MarketCouponMapper mapper = session.getMapper(MarketCouponMapper.class);
        mapper.insert(marketCoupon);
        session.commit();
        session.close();
        return;
    }
}

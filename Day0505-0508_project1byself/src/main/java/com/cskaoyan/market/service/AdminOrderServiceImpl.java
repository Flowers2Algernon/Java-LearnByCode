package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.*;
import com.cskaoyan.market.db.mapper.MarketOrderGoodsMapper;
import com.cskaoyan.market.db.mapper.MarketOrderMapper;
import com.cskaoyan.market.db.mapper.MarketUserMapper;
import com.cskaoyan.market.util.MybatisUtil;
import com.cskaoyan.market.vo.MarketOrderDetailsUser;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminOrderServiceImpl implements AdminOrderService {
    @Override
    public List<MarketOrder> list(Integer page, Integer limit, String orderStatus, String sort, String order, LocalDateTime start, LocalDateTime end, String orderSn, Integer orderId, Integer userId) {
        SqlSession session = MybatisUtil.getSession();
        MarketOrderMapper mapper = session.getMapper(MarketOrderMapper.class);
        MarketOrderExample marketOrderExample = new MarketOrderExample();
        marketOrderExample.setOrderByClause(sort + " "+ order);
        MarketOrderExample.Criteria criteria = marketOrderExample.createCriteria();
//        if (!StringUtils.isEmpty(start.toString())&&!StringUtils.isEmpty(end.toString())){
//            criteria.andAddTimeBetween(start,end);
//        }
//        if (orderId!=null){
//            criteria.andIdEqualTo(orderId);
//        }
//        if (!StringUtils.isEmpty(orderSn)){
//            criteria.andOrderSnLike(orderSn);
//        }
//        if (userId!=null){
//            criteria.andUserIdEqualTo(userId);
//        }
        PageHelper.startPage(page,limit);
        List<MarketOrder> list = mapper.selectByExample(marketOrderExample);
        session.commit();
        session.close();
        return list;
    }

    @Override
    public HashMap<String, Object> details(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        MarketOrderMapper orderMapper = session.getMapper(MarketOrderMapper.class);
        MarketUserMapper userMapper = session.getMapper(MarketUserMapper.class);
        MarketOrderGoodsMapper marketOrderGoodsMapper = session.getMapper(MarketOrderGoodsMapper.class);
        MarketOrderExample marketOrderExample = new MarketOrderExample();
        MarketOrderExample.Criteria orderExampleCriteria = marketOrderExample.createCriteria();
        orderExampleCriteria.andIdEqualTo(id);
        MarketOrder marketOrder = orderMapper.selectByPrimaryKey(id);
        ArrayList<Object> list = new ArrayList<>();
        list.add(marketOrder);
        MarketOrderGoods marketOrderGoods = marketOrderGoodsMapper.selectByPrimaryKey(id);
        list.add(marketOrderGoods);
        Integer userId = marketOrder.getUserId();
        MarketUserExample marketUserExample = new MarketUserExample();
        MarketUserExample.Criteria userExampleCriteria = marketUserExample.createCriteria();
        userExampleCriteria.andIdEqualTo(userId);
        MarketUser marketUser = userMapper.selectOneByExample(marketUserExample);
        MarketOrderDetailsUser marketOrderDetailsUser = new MarketOrderDetailsUser();
        marketOrderDetailsUser.setAvatar(marketUser.getAvatar());
        marketOrderDetailsUser.setNickname(marketUser.getNickname());
        list.add(marketOrderDetailsUser);
        HashMap<String, Object> map = new HashMap<>();
        map.put("order",marketOrder);
        map.put("orderGoods",marketOrderGoods);
        map.put("user",marketOrderDetailsUser);
        return map;
    }
}

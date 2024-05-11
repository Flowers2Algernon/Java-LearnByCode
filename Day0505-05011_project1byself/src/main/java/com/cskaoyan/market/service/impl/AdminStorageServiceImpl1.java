package com.cskaoyan.market.service.impl;

import com.cskaoyan.market.db.domain.MarketStorage;
import com.cskaoyan.market.db.domain.MarketStorageExample;
import com.cskaoyan.market.db.mapper.MarketStorageMapper;
import com.cskaoyan.market.service.AdminStorageService;
import com.cskaoyan.market.util.MybatisUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.List;

public class AdminStorageServiceImpl1 implements AdminStorageService {
    @Override
    public void create(MarketStorage marketStorage) {
        //此处实现数据库中具体的插入逻辑
        SqlSession session = MybatisUtil.getSession();
        MarketStorageMapper mapper = session.getMapper(MarketStorageMapper.class);
        mapper.insert(marketStorage);
        session.commit();
        session.close();
    }

    @Override
    public List<MarketStorage> list(Integer limit, Integer page, String key, String name, String sort, String order) {
        SqlSession session = MybatisUtil.getSession();
        MarketStorageMapper mapper = session.getMapper(MarketStorageMapper.class);
        MarketStorageExample marketStorageExample = new MarketStorageExample();
        marketStorageExample.setOrderByClause(sort + " " + order);
        MarketStorageExample.Criteria criteria = marketStorageExample.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyLike("%" + key + "%");
        }
        //分页
        PageHelper.startPage(page, limit);
        //执行查询操作
        List<MarketStorage> marketStorages = mapper.selectByExample(marketStorageExample);
        //不要忘记提交数据库
        session.commit();
        session.close();
        return marketStorages;
    }

    @Override
    public void update(MarketStorage marketStorage) {
        SqlSession session = MybatisUtil.getSession();
        MarketStorageMapper mapper = session.getMapper(MarketStorageMapper.class);
        mapper.updateByPrimaryKeySelective(marketStorage);
        session.commit();
        session.close();
    }

    @Override
    public void delete(MarketStorage marketStorage) {
        SqlSession session = MybatisUtil.getSession();
        MarketStorageMapper mapper = session.getMapper(MarketStorageMapper.class);
        mapper.deleteByPrimaryKey(marketStorage.getId());
        session.commit();
        session.close();
    }
}

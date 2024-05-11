package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketSystem;
import com.cskaoyan.market.db.domain.MarketSystemExample;
import com.cskaoyan.market.db.mapper.MarketSystemMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdminConfigServiceImpl implements AdminConfigService {
    @Override
    public void mallPost(HashMap<String, String> map) {
        SqlSession session = MybatisUtil.getSession();
        MarketSystemMapper mapper = session.getMapper(MarketSystemMapper.class);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        MarketSystemExample marketSystemExample = new MarketSystemExample();
        MarketSystemExample.Criteria criteria = marketSystemExample.createCriteria();
        for (Map.Entry<String, String> entry : entries) {
            //应该先get后set
            criteria.andKeyNameEqualTo(entry.getKey());
            MarketSystem marketSystem = mapper.selectOneByExample(marketSystemExample);
            if (marketSystem==null){
                marketSystem = new MarketSystem();
            }
            marketSystem.setKeyName(entry.getKey());
            marketSystem.setKeyValue(entry.getValue());
            mapper.insert(marketSystem);
            session.commit();
        }
        session.close();
    }

    @Override
    public HashMap<String, String> mallGet(ArrayList<String> list) {
        SqlSession session = MybatisUtil.getSession();
        MarketSystemMapper mapper = session.getMapper(MarketSystemMapper.class);
        HashMap<String, String> mapResults = new HashMap<>();
        for (String s : list) {
            MarketSystemExample marketSystemExample = new MarketSystemExample();
            MarketSystemExample.Criteria criteria = marketSystemExample.createCriteria();
            criteria.andKeyNameEqualTo(s);
            MarketSystem marketSystem = mapper.selectOneByExample(marketSystemExample);
            session.commit();
            mapResults.put(marketSystem.getKeyName(),marketSystem.getKeyValue());
        }
        session.close();
        return mapResults;
    }


}

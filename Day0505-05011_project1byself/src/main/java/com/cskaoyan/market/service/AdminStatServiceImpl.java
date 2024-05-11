package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketUser;
import com.cskaoyan.market.db.domain.MarketUserExample;
import com.cskaoyan.market.db.mapper.MarketUserMapper;
import com.cskaoyan.market.util.MybatisUtil;
import com.cskaoyan.market.vo.AdminStatUserBo;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminStatServiceImpl implements AdminStatService {
    @Override
    public List<AdminStatUserBo> getDayAndUserCount() {
        SqlSession session = MybatisUtil.getSession();
        MarketUserMapper mapper = session.getMapper(MarketUserMapper.class);
        MarketUserExample marketUserExample = new MarketUserExample();
        List<MarketUser> marketUsers = mapper.selectByExample(marketUserExample);
        System.out.println(marketUsers.get(0)+"=====================");
        //此处是从数据库中读出了数据的
        HashMap<String, Integer> dateAndUserCountMap = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (MarketUser marketUser : marketUsers) {
            LocalDateTime addTime = marketUser.getAddTime();
//            LocalDateTime.parse()
//            dateAndUserCountMap.put(format,dateAndUserCountMap.get(marketUser.getAddTime().format(dateTimeFormatter))+1);
        }
        System.out.println(dateAndUserCountMap.toString()+"=======================");
        Set<Map.Entry<String, Integer>> entries = dateAndUserCountMap.entrySet();
        List<AdminStatUserBo> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            AdminStatUserBo adminStatUserBo = new AdminStatUserBo();
            adminStatUserBo.setDay(entry.getKey());
            System.out.println(adminStatUserBo.getDay()+"===============================");
            adminStatUserBo.setUserCount(entry.getValue());
            list.add(adminStatUserBo);
        }
        session.commit();
        session.close();
        return list;
    }
}

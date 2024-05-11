package com.cskaoyan.market.service.impl;

import com.cskaoyan.market.db.domain.MarketUser;
import com.cskaoyan.market.db.domain.MarketUserExample;
import com.cskaoyan.market.db.mapper.MarketUserMapper;
import com.cskaoyan.market.service.MarketUserService;
import com.cskaoyan.market.util.MybatisUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MarketUserServiceImpl implements MarketUserService {
    //todo 当查询完id后，再清除输入的id语句，查询全部用户时，此时无法正常显示
    @Override
    public List<MarketUser> list(Integer limit, Integer page, String username, String mobile, Integer id, String sort, String order) {
        //此处是利用动态sql语句去数据库中查询数据，并返回具体的参数
        //此处具体的参数是指一个由marketUser组成的list
        SqlSession session = MybatisUtil.getSession();
        MarketUserMapper marketUserMapper = session.getMapper(MarketUserMapper.class);
        MarketUserExample marketUserExample = new MarketUserExample();
        marketUserExample.setOrderByClause(sort+" "+order);
        MarketUserExample.Criteria criteria = marketUserExample.createCriteria();
        if (!StringUtils.isEmpty(username)){
            criteria.addUsernameLike("%"+username+"%");
        }
        if (!StringUtils.isEmpty(mobile)){
            criteria.addMobileLike("%"+mobile+"%");
        }
        if (id!=null){
            criteria.addIdLike("%"+id+"%");
        }
        //在执行查询之前，如果添加下面这行代码，就表示执行分页操作，如果不添加这行代码，就表示不分页操作
        PageHelper.startPage(page,limit);
        List<MarketUser> marketUsers = marketUserMapper.selectByExampleSelective(marketUserExample, MarketUser.Column.addTime, MarketUser.Column.avatar, MarketUser.Column.deleted, MarketUser.Column.gender, MarketUser.Column.id, MarketUser.Column.lastLoginIp, MarketUser.Column.lastLoginTime,
                MarketUser.Column.mobile, MarketUser.Column.nickname, MarketUser.Column.password, MarketUser.Column.sessionKey, MarketUser.Column.status, MarketUser.Column.updateTime,
                MarketUser.Column.userLevel, MarketUser.Column.username, MarketUser.Column.weixinOpenid);
        session.commit();
        session.close();
        return marketUsers;
    }
}

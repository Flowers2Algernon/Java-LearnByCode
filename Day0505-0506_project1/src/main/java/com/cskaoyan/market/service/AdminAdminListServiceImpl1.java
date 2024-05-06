package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.*;
import com.cskaoyan.market.db.mapper.MarketAdminMapper;
import com.cskaoyan.market.util.MybatisUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class AdminAdminListServiceImpl1 implements AdminAdminListService {
    @Override
    public List<MarketAdminListVo> list(Integer limit, Integer page, String username, String sort, String order) {
        SqlSession session = MybatisUtil.getSession();
        MarketAdminMapper mapper = session.getMapper(MarketAdminMapper.class);
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria criteria = marketAdminExample.createCriteria();
        marketAdminExample.setOrderByClause(sort+" "+order);
        if (!StringUtils.isEmpty(username)){
            criteria.addUsernameEqualTo(username);
        }
        //分页操作
        PageHelper.startPage(page, limit);
        List<MarketAdmin> marketAdmins =mapper.selectByExampleSelective(marketAdminExample, MarketAdmin.Column.id, MarketAdmin.Column.avatar, MarketAdmin.Column.roleIds, MarketAdmin.Column.username);
        //此处需要把实体对象marketAdmin转换为新创建的marketAdminListVo
        session.commit();
        session.close();
        ArrayList<MarketAdminListVo> marketAdminListVos = new ArrayList<>();
        for (MarketAdmin marketAdmin : marketAdmins) {
            marketAdminListVos.add(new MarketAdminListVo(marketAdmin.getId(),marketAdmin.getUsername(),marketAdmin.getAvatar(),marketAdmin.getRoleIds()));
        }

        return marketAdminListVos;
    }
}

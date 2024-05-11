package com.cskaoyan.market.service.impl;

import com.cskaoyan.market.db.domain.*;
import com.cskaoyan.market.db.mapper.*;
import com.cskaoyan.market.service.MarketGoodsService;
import com.cskaoyan.market.util.MybatisUtil;
import com.cskaoyan.market.vo.CatAndBrandVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketGoodsServiceImpl1 implements MarketGoodsService {
    @Override
    public void create(MarketGoods marketGoods, List<MarketGoodsSpecification> marketGoodsSpecifications, List<MarketGoodsAttribute> attributes, List<MarketGoodsProduct> products) {
        //需要插入4张表
        //其中一张goods表，三张goods的附表
        //先插入goods表
        SqlSession session = MybatisUtil.getSession();
        MarketGoodsMapper marketGoodsMapper = session.getMapper(MarketGoodsMapper.class);
        MarketGoodsAttributeMapper marketGoodsAttributeMapper = session.getMapper(MarketGoodsAttributeMapper.class);
        MarketGoodsProductMapper marketGoodsProductMapper = session.getMapper(MarketGoodsProductMapper.class);
        MarketGoodsSpecificationMapper marketGoodsSpecificationMapper = session.getMapper(MarketGoodsSpecificationMapper.class);
        LocalDateTime now = LocalDateTime.now();
        //首先插入goods表
        //此处处理零售价格--几个价格中最低的价格
        BigDecimal price = products.get(0).getPrice();
        for (MarketGoodsProduct product : products) {
            BigDecimal decimal = product.getPrice();
            if (price.compareTo(decimal) == 1) {
                //此时表示price的值大于decimal的值，将其值赋给price
                price = decimal;
            }
        }
        marketGoods.setRetailPrice(price);
        marketGoods.setUpdateTime(now);
        marketGoods.setAddTime(now);
        marketGoods.setDeleted(false);

        //接下来是同时插入四张表
        try {
            marketGoodsMapper.insertSelective(marketGoods);
            Integer id = marketGoods.getId();

            //此处对每一个specification来键入值
            for (MarketGoodsSpecification marketGoodsSpecification : marketGoodsSpecifications) {
                marketGoodsSpecification.setGoodsId(id);
                marketGoodsSpecification.setAddTime(now);
                marketGoodsSpecification.setUpdateTime(now);
                marketGoodsSpecification.setDeleted(false);
                marketGoodsSpecificationMapper.insertSelective(marketGoodsSpecification);
            }
            for (MarketGoodsProduct product : products) {
                product.setGoodsId(id);
                product.setAddTime(now);
                product.setUpdateTime(now);
                product.setDeleted(false);
                marketGoodsProductMapper.insertSelective(product);
            }
            for (MarketGoodsAttribute attribute : attributes) {
                attribute.setDeleted(false);
                attribute.setGoodsId(id);
                attribute.setAddTime(now);
                attribute.setUpdateTime(now);
                marketGoodsAttributeMapper.insertSelective(attribute);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<MarketGoods> list(Integer limit, Integer page, String goodsSn, String goodsId, String order, String sort, String name) {
        //此处就是连接数据库，做校验，数据库查询后得到信息并返回给controller层
        SqlSession session = MybatisUtil.getSession();
        MarketGoodsMapper mapper = session.getMapper(MarketGoodsMapper.class);
        //当有一些规则时，需要用example来进行处理
        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria = marketGoodsExample.createCriteria();
        //排序规则
        marketGoodsExample.setOrderByClause(sort + " " + order);
        //根据传入的参数的值，来进行相应的判断
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andIdEqualTo(Integer.parseInt(goodsId));
        }
        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andGoodsSnEqualTo(goodsSn);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        //分页查询
        PageHelper.startPage(page, limit);
        //查询操作
        List<MarketGoods> list = mapper.selectByExample(marketGoodsExample);
        //提交查询
        session.commit();
        session.close();
        return list;
    }

    @Override
    public Map<String, Object> catAndBrand() {
        //首先需要查询数据库，得到两张表
        //全数查询即可
        SqlSession session = MybatisUtil.getSession();
        MarketBrandMapper marketBrandMapper = session.getMapper(MarketBrandMapper.class);
        MarketCategoryMapper marketCategoryMapper = session.getMapper(MarketCategoryMapper.class);

        List<MarketBrand> marketBrands = marketBrandMapper.selectByExample(new MarketBrandExample());
        List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(new MarketCategoryExample());

        session.commit();
        session.close();

        //接下来处理数据
        Map<String, Object> resultMap = new HashMap<>();
        List<CatAndBrandVo> brandList = new ArrayList<>();
        List<CatAndBrandVo> categoryList = new ArrayList<>();

        //根据PID的数值将商品进行分类
        Map<Integer, List<MarketCategory>> categoriesByPid = groupById(marketCategories);
        List<MarketCategory> l1categories = categoriesByPid.get(0);
        for (MarketCategory l1category : l1categories) {
            //此时得到的是l1下所有的l2的信息
            List<CatAndBrandVo> childrenOfL1 = new ArrayList<>();
            CatAndBrandVo l1Vo = new CatAndBrandVo(l1category.getName(), l1category.getId(), childrenOfL1);
            categoryList.add(l1Vo);
            List<MarketCategory> l2Categories = categoriesByPid.get(l1category.getPid());
            if (l2Categories!=null&&l2Categories.size()>0){
                for (MarketCategory l2Category : l2Categories) {
                    childrenOfL1.add(new CatAndBrandVo(l2Category.getName(),l2Category.getId(),null));
                }
            }
        }

        ///接下来处理品牌信息
        for (MarketBrand marketBrand : marketBrands) {
            brandList.add(new CatAndBrandVo(marketBrand.getName(),marketBrand.getId(),null));
        }
        resultMap.put("categoryList",categoryList);
        resultMap.put("brandList",brandList);
        return  resultMap;
    }

    private Map<Integer, List<MarketCategory>> groupById(List<MarketCategory> marketCategories) {
        HashMap<Integer, List<MarketCategory>> categoriesByPid = new HashMap<>();
        for (MarketCategory marketCategory : marketCategories) {
            Integer pid = marketCategory.getPid();
            List<MarketCategory> list = categoriesByPid.get(pid);
            if (list==null){
                list = new ArrayList<>();
            }
            list.add(marketCategory);
            categoriesByPid.put(pid,list);
        }
        return categoriesByPid;
    }
}

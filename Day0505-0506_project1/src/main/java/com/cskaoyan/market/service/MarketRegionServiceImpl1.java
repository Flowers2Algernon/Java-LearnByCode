package com.cskaoyan.market.service;

import com.cskaoyan.market.db.domain.MarketRegion;
import com.cskaoyan.market.db.domain.MarketRegionExample;
import com.cskaoyan.market.db.domain.MarketRegionVo;
import com.cskaoyan.market.db.mapper.MarketRegionMapper;
import com.cskaoyan.market.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketRegionServiceImpl1 implements MarketRegionService {
    @Override
    public List<MarketRegionVo> list() {
        //此处实现具体的list查询逻辑
        //先连接数据库
        SqlSession session = MybatisUtil.getSession();
        MarketRegionMapper mapper = session.getMapper(MarketRegionMapper.class);
        //将整张表数据全部查询出来
        List<MarketRegion> marketRegions = mapper.selectByExample(new MarketRegionExample());
        session.commit();
        session.close();

        //获取第一层省份数据，按照Pid来进行分组
        Map<Integer,List<MarketRegion>> regionMap = groupByPidRegion(marketRegions);
        //此时得到的是按pid进行分组的数据，接下来需要将其转换为view层返回需要的数据

        List<MarketRegionVo> marketRegionVos = new ArrayList<>();
        List<MarketRegion> regionList = regionMap.get(0);//取出全部的省集合
        for (MarketRegion province : regionList) {
            //省，
            Integer provinceId = province.getId();
            List<MarketRegionVo> cityVoList = new ArrayList<>();
            //此处的cityVoList是用来放市的
            MarketRegionVo provinceVo = new MarketRegionVo(province.getId(), province.getName(), province.getType(), province.getCode(), cityVoList);
            List<MarketRegion> citiesBelongToEachProvince = regionMap.get(provinceId);
            for (MarketRegion city : citiesBelongToEachProvince) {
                //市
                Integer cityId = city.getId();
                //此areaVoList表示市下面所有的区
                ArrayList<MarketRegionVo> areaVoList = new ArrayList<>();
                MarketRegionVo citiVO = new MarketRegionVo(cityId, city.getName(), city.getType(), city.getCode(), areaVoList);
                List<MarketRegion> areas = regionMap.get(cityId);
                for (MarketRegion area : areas) {
                  areaVoList.add(new MarketRegionVo(area.getId(),area.getName(),area.getType(),area.getCode(),null));
                }
                //区全部加完之后，将省下面的市全部加入进去
                cityVoList.add(citiVO);
            }
            //将省vo放入list中
            marketRegionVos.add(provinceVo);
        }
        return marketRegionVos;

    }

    private Map<Integer, List<MarketRegion>> groupByPidRegion(List<MarketRegion> marketRegions) {
        HashMap<Integer, List<MarketRegion>> regionMap = new HashMap<>();
        for (MarketRegion marketRegion : marketRegions) {
            Integer pid = marketRegion.getPid();
            //先get 后set
            List<MarketRegion> list = regionMap.get(pid);
            if (list==null){
                list = new ArrayList<>();
            }
            list.add(marketRegion);
            regionMap.put(pid,list);
        }
        return regionMap;
    }
}

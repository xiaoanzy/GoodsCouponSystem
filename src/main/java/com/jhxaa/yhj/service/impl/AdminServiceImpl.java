package com.jhxaa.yhj.service.impl;

import com.jhxaa.yhj.mapper.TGoodsInfoMapper;
import com.jhxaa.yhj.mapper.TGoodsInfoProblemMapper;
import com.jhxaa.yhj.mapper.TUserMapper;
import com.jhxaa.yhj.service.AdminService;
import com.jhxaa.yhj.utli.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    static Logger LOG = LogUtil.getLog(AdminServiceImpl.class);

    @Autowired(required = false)
    TGoodsInfoMapper tGoodsInfoMapper;
    @Autowired(required = false)
    TUserMapper tUserMapper;
    @Autowired(required = false)
    TGoodsInfoProblemMapper tGoodsInfoProblemMapper;

    @Override
    public Map<String, Integer> selectCount() {
        //[{"effective_type":1,"count":21098},{"effective_type":2,"count":24266}]
        List<Map<String, Integer>> goodsInfoByEffectiveTypeGroup = tGoodsInfoMapper.findGoodsInfoByEffectiveTypeGroup();
        Integer goodsInfoCount = tGoodsInfoMapper.findGoodsInfoCount();
        Integer goodsInfoCountByCreateTime = tGoodsInfoMapper.findGoodsInfoCountByCreateTime();
        Integer userCount = tUserMapper.findUserCount();
        Integer goodsInfoProblemCount = tGoodsInfoProblemMapper.findGoodsInfoProblemCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("goodsInfoCount", goodsInfoCount);
        map.put("goodsInfoProblemCount", goodsInfoProblemCount);
        map.put("newCount", goodsInfoCountByCreateTime);
        map.put("userCount", userCount);
        map.putAll(resultConvert(goodsInfoByEffectiveTypeGroup));
        return map;
    }

    @Async
    @Override
    public void clearRepeatGoods() {
        LOG.info("已开始clearRepeatGoods方法。。。");
        List<Map<String, Long>> mapList = null;
        List<Integer> tGoodsInfoList = null;
        Integer integer = 0;
        List<Integer> integerArrayList = new ArrayList<>();
        mapList = tGoodsInfoMapper.statisticalRepeatGoodsId();
        for (Map<String, Long> longMap : mapList) {
            Long goodsId = longMap.get("goodsId");
            Long count = longMap.get("count");
            tGoodsInfoList = tGoodsInfoMapper.findKeyByGoodsId(goodsId, count.intValue());
            integerArrayList.addAll(tGoodsInfoList);
        }
        LOG.warn(String.format("数据提取完成个数[%s]", mapList.size()));
        if (null != tGoodsInfoList && tGoodsInfoList.size() > 0) {
            integer = tGoodsInfoMapper.updateFailureGoodsInfoByPrimaryKey(integerArrayList);
        }
        LOG.warn(String.format("已更新[%s]个商品", integer));
        mapList = null;
        tGoodsInfoList = null;
        integer = null;
        integerArrayList = null;
    }

    private Map<String, Integer> resultConvert(List<Map<String, Integer>> liatMap) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < liatMap.size(); i++) {
            Map<String, Integer> stringIntegerMap = liatMap.get(i);
            int effective_type = stringIntegerMap.get("effective_type");
            Number number = (Number) stringIntegerMap.get("count");
            Integer count = number.intValue();
            if (effective_type == 1) {
                map.put("effective", count);
            }
            if (effective_type == 2) {
                map.put("invalid", count);
            }
        }
        return map;
    }
}

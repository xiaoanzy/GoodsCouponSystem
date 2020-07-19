package com.jhxaa.yhj.executor.impl;

import com.jhxaa.yhj.executor.FindCategoryGoodsInfoByConditionsExecutor;
import com.jhxaa.yhj.mapper.TGoodsInfoMapper;
import com.jhxaa.yhj.pojo.TGoodsInfo;

import java.util.List;

public class FindCategoryGoodsInfoByPrice9ExecutorImpl extends FindCategoryGoodsInfoByConditionsExecutor {


    @Override
    public List<TGoodsInfo> findCategoryGoodsInfoByConditions(
            TGoodsInfoMapper tGoodsInfoMapper,
            FindCategoryGoodsInfoByConditions findCategoryGoodsInfoByConditions) {

        /**
         *                                                              String goodsName,
         *                                                              Integer pageIndex,
         *                                                              Integer pageSize,
         *                                                              BigDecimal startPrice,
         *                                                              BigDecimal endPrice,
         *                                                              String screeningType,
         *                                                              String navType
         */
        return tGoodsInfoMapper.findPrice9CategoryGoodsInfoByConditions(
                findCategoryGoodsInfoByConditions.getGoodsName(),
                findCategoryGoodsInfoByConditions.getPageIndex(),
                findCategoryGoodsInfoByConditions.getPageSize(),
                findCategoryGoodsInfoByConditions.getStartPrice(),
                findCategoryGoodsInfoByConditions.getEndPrice(),
                findCategoryGoodsInfoByConditions.getScreeningType(),
                findCategoryGoodsInfoByConditions.getNavType()
        );
    }
}

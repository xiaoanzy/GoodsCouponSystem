package com.jhxaa.yhj.executor.impl;

import com.jhxaa.yhj.executor.FindCategoryGoodsInfoByConditionsExecutor;
import com.jhxaa.yhj.mapper.TGoodsInfoMapper;
import com.jhxaa.yhj.pojo.TGoodsInfo;

import java.util.List;

public class FindCategoryGoodsInfoByNewExecutorImpl extends FindCategoryGoodsInfoByConditionsExecutor {


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
        return tGoodsInfoMapper.findNewCategoryGoodsInfoByConditions(
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

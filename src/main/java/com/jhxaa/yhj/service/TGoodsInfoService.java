package com.jhxaa.yhj.service;

import com.jhxaa.yhj.pojo.Page;
import com.jhxaa.yhj.pojo.TGoodsInfo;
import com.jhxaa.yhj.request.SelectGoodsInfoListRequestBean;

import java.util.List;

public interface TGoodsInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfo record);

    TGoodsInfo selectByPrimaryKey(Integer id);

    List<TGoodsInfo> selectAll();

    int updateByPrimaryKey(TGoodsInfo record);

    boolean batchReadExclInsertTGoodsInfo();


    Page findPageGoodsInfoList(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean);


    List<String> findTopKeywordByRandom();

    List<String> findCategoryPageGoodsInfoList();

    List<TGoodsInfo> findLoveGoodsListByCatoryName(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean);

    Boolean checkTGoodsInfo();


    // Integer findNowPreferentialGoodsCount();

    List<TGoodsInfo> findNowPreferentialGoodsList(Integer indexSize);

    // Integer findNowTopGoodsCount();

    List<TGoodsInfo> findNowTopGoodsList(Integer indexSize);

}

package com.jhxaa.yhj.service;

import com.jhxaa.yhj.pojo.Page;
import com.jhxaa.yhj.request.SelectGoodsInfoListRequestBean;

public interface TGoodsInfoProblemService {


    void insert(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean);

    Page getTGoodsInfoProblemList(Integer index, Integer size);


    void modifyProbliemGoodsState(Integer id, Integer state);

}

package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.pojo.TRoommatesMoneySystemCurrentMoney;

import java.util.List;

public interface TRoommatesMoneySystemCurrentMoneyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRoommatesMoneySystemCurrentMoney record);

    TRoommatesMoneySystemCurrentMoney selectByPrimaryKey(Integer id);

    List<TRoommatesMoneySystemCurrentMoney> selectAll();

    int updateByPrimaryKey(TRoommatesMoneySystemCurrentMoney record);
}
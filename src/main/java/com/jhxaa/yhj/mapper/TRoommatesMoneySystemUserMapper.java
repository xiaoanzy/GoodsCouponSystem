package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.pojo.TRoommatesMoneySystemUser;

import java.util.List;

public interface TRoommatesMoneySystemUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRoommatesMoneySystemUser record);

    TRoommatesMoneySystemUser selectByPrimaryKey(Integer id);

    List<TRoommatesMoneySystemUser> selectAll();

    int updateByPrimaryKey(TRoommatesMoneySystemUser record);

    TRoommatesMoneySystemUser findUserByUserObject(TRoommatesMoneySystemUser tRoommatesMoneySystemUser);
}
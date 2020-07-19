package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.pojo.TUser;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);


    TUser selectByTUserObject(TUser record);


    Integer findUserCount();

}
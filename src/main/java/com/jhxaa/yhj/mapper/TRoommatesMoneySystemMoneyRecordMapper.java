package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.pojo.TRoommatesMoneySystemMoneyRecord;

import java.util.List;
import java.util.Map;

public interface TRoommatesMoneySystemMoneyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRoommatesMoneySystemMoneyRecord record);

    TRoommatesMoneySystemMoneyRecord selectByPrimaryKey(Integer id);

    List<TRoommatesMoneySystemMoneyRecord> selectAll();

    int updateByPrimaryKey(TRoommatesMoneySystemMoneyRecord record);


    List<Map<String, ? extends Object>> findMoneyRecordAll();
}
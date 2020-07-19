package com.jhxaa.yhj.service;

import com.jhxaa.yhj.pojo.TRoommatesMoneySystemCurrentMoney;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemUser;
import com.jhxaa.yhj.vo.RecordMoneyVo;

import java.util.List;

public interface RoomMoneyService {

    String doLogin(String userName, String password);

    Boolean logout(String token);

    List<RecordMoneyVo> selectMoneyRecordAll(String token);

    void reportMoney(Integer recordType, double money, String token);

    TRoommatesMoneySystemCurrentMoney findCurrentMoney();

    TRoommatesMoneySystemUser getUserBytoken(String token);

}

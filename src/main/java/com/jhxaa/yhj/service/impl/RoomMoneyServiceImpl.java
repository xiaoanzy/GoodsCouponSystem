package com.jhxaa.yhj.service.impl;

import com.alibaba.fastjson.JSON;
import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.mapper.TRoommatesMoneySystemCurrentMoneyMapper;
import com.jhxaa.yhj.mapper.TRoommatesMoneySystemMoneyRecordMapper;
import com.jhxaa.yhj.mapper.TRoommatesMoneySystemUserMapper;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemCurrentMoney;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemMoneyRecord;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemUser;
import com.jhxaa.yhj.service.RoomMoneyService;
import com.jhxaa.yhj.utli.CommonUtil;
import com.jhxaa.yhj.utli.EmptyUtil;
import com.jhxaa.yhj.utli.JwtUtil;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.vo.RecordMoneyVo;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoomMoneyServiceImpl implements RoomMoneyService {

    static Logger LOG = LogUtil.getLog(RoomMoneyServiceImpl.class);

    @Autowired(required = false)
    TRoommatesMoneySystemUserMapper tRoommatesMoneySystemUserMapper;

    @Autowired(required = false)
    TRoommatesMoneySystemMoneyRecordMapper tRoommatesMoneySystemMoneyRecordMapper;

    @Autowired(required = false)
    TRoommatesMoneySystemCurrentMoneyMapper tRoommatesMoneySystemCurrentMoneyMapper;

    @Override
    public String doLogin(String userName, String password) {
        TRoommatesMoneySystemUser systemUser = new TRoommatesMoneySystemUser();
        systemUser.setfUserName(userName);
        systemUser.setfPassword(password);
        TRoommatesMoneySystemUser byUserObject = tRoommatesMoneySystemUserMapper.findUserByUserObject(systemUser);
        if (EmptyUtil.isEmpty(byUserObject)) {
            throw new BusiException(ExceptionEnum.USER_CHECK_FAIL.value());
        }
        String token = JwtUtil.create(byUserObject.getfUserName(), byUserObject);
        return token;
    }

    @Override
    public Boolean logout(String token) {
        return null;
    }

    @Override
    public List<RecordMoneyVo> selectMoneyRecordAll(String token) {
//        List<TRoommatesMoneySystemMoneyRecord> tRoommatesMoneySystemMoneyRecordList = tRoommatesMoneySystemMoneyRecordMapper.selectAll();
        List<Map<String, ?>> moneyRecordAll = tRoommatesMoneySystemMoneyRecordMapper.findMoneyRecordAll();
        List<RecordMoneyVo> list = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(moneyRecordAll)) {
            for (Map<String, ?> stringMap : moneyRecordAll) {
                String name = String.valueOf(stringMap.get("name"));
                BigDecimal useMoney = new BigDecimal(String.valueOf(stringMap.get("useMoney")));
                BigDecimal leaveMoney = new BigDecimal(String.valueOf(stringMap.get("leaveMoney")));
                Date createTime = (Date) stringMap.get("createTime");
                Integer recordType = (Integer) stringMap.get("recordType");
                list.add(new RecordMoneyVo(
                        name, useMoney, leaveMoney, createTime, recordType));
            }
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reportMoney(Integer recordType, double money, String token) {
        Claims checkToken = JwtUtil.checkToken(token);
        String subject = checkToken.getSubject();
        TRoommatesMoneySystemUser user = JSON.parseObject(subject, TRoommatesMoneySystemUser.class);
        LOG.warn(String.format("当前[%s]进入上报方法，上报金额[%s]元，执行的是[%s]方法", user.getfName(), money, recordType == 1 ? "添加金额" : "使用金额"));
        //根据DB——key查询对象
        TRoommatesMoneySystemCurrentMoney moneySystemCurrentMoney = tRoommatesMoneySystemCurrentMoneyMapper.selectByPrimaryKey(Common.SYSTEM_DB_ID_1);
        BigDecimal addMoney = CommonUtil.getBigDecimal(money);
        BigDecimal getfCurrentMoney = moneySystemCurrentMoney.getfCurrentMoney();
        //1 添加      2 更新
        if (1 == recordType) {
            getfCurrentMoney = getfCurrentMoney.add(addMoney);
        } else if (2 == recordType) {
            getfCurrentMoney = getfCurrentMoney.subtract(addMoney);
        }
        TRoommatesMoneySystemCurrentMoney tempCurrentMoney =
                new TRoommatesMoneySystemCurrentMoney(Common.SYSTEM_DB_ID_1, getfCurrentMoney, new Date(), user.getId());
        //更新资金表的数据
        int updateByPrimaryKey = tRoommatesMoneySystemCurrentMoneyMapper.updateByPrimaryKey(tempCurrentMoney);
        if (updateByPrimaryKey >= 1) {
            TRoommatesMoneySystemMoneyRecord record = new TRoommatesMoneySystemMoneyRecord();
            record.setfCreateTime(new Date());
            record.setfUserId(user.getId());
            record.setfLeaveMoney(getfCurrentMoney);//留下的钱
            record.setfUseMoney(addMoney);//使用的钱
            record.setfRecordType(recordType);
            int insert = tRoommatesMoneySystemMoneyRecordMapper.insert(record);
            if (insert >= 1) {
                LOG.info("数据上报成功！！！");
            }
        }
    }

    @Override
    public TRoommatesMoneySystemCurrentMoney findCurrentMoney() {
        TRoommatesMoneySystemCurrentMoney currentMoney = tRoommatesMoneySystemCurrentMoneyMapper.selectByPrimaryKey(Common.SYSTEM_DB_ID_1);
        if (EmptyUtil.isEmpty(currentMoney)) {
            currentMoney = new TRoommatesMoneySystemCurrentMoney();
        }
        return currentMoney;
    }

    @Override
    public TRoommatesMoneySystemUser getUserBytoken(String token) {
        Claims claims = JwtUtil.checkToken(token);
        String subject = claims.getSubject();
        TRoommatesMoneySystemUser user = JSON.parseObject(subject, TRoommatesMoneySystemUser.class);
        return user;
    }
}

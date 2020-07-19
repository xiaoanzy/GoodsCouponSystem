package com.jhxaa.yhj.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RecordMoneyVo {

    String name;
    BigDecimal useMoney;
    BigDecimal leaveMoney;
    Date createTime;
    Integer recordType;

    public RecordMoneyVo(String name, BigDecimal useMoney, BigDecimal leaveMoney, Date createTime, Integer recordType) {
        this.name = name;
        this.useMoney = useMoney;
        this.leaveMoney = leaveMoney;
        this.createTime = createTime;
        this.recordType = recordType;
    }


    public RecordMoneyVo() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public BigDecimal getLeaveMoney() {
        return leaveMoney;
    }

    public void setLeaveMoney(BigDecimal leaveMoney) {
        this.leaveMoney = leaveMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
}

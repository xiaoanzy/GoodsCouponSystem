package com.jhxaa.yhj.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TRoommatesMoneySystemMoneyRecord {
    private Integer id;

    private Integer fUserId;

    private BigDecimal fUseMoney;

    private BigDecimal fLeaveMoney;

    private Date fCreateTime;

    private Integer fRecordType;

    public Integer getfRecordType() {
        return fRecordType;
    }

    public void setfRecordType(Integer fRecordType) {
        this.fRecordType = fRecordType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfUserId() {
        return fUserId;
    }

    public void setfUserId(Integer fUserId) {
        this.fUserId = fUserId;
    }

    public BigDecimal getfUseMoney() {
        return fUseMoney;
    }

    public void setfUseMoney(BigDecimal fUseMoney) {
        this.fUseMoney = fUseMoney;
    }

    public BigDecimal getfLeaveMoney() {
        return fLeaveMoney;
    }

    public void setfLeaveMoney(BigDecimal fLeaveMoney) {
        this.fLeaveMoney = fLeaveMoney;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }
}
package com.jhxaa.yhj.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TRoommatesMoneySystemCurrentMoney {
    private Integer id;

    private BigDecimal fCurrentMoney;

    private Date fCreateTime;

    private Date fModifyTime;

    private Integer fModifyUserId;


    public TRoommatesMoneySystemCurrentMoney() {
    }

    public TRoommatesMoneySystemCurrentMoney(Integer id, BigDecimal fCurrentMoney, Date fCreateTime, Date fModifyTime, Integer fModifyUserId) {
        this.id = id;
        this.fCurrentMoney = fCurrentMoney;
        this.fCreateTime = fCreateTime;
        this.fModifyTime = fModifyTime;
        this.fModifyUserId = fModifyUserId;
    }

    public TRoommatesMoneySystemCurrentMoney(Integer id, BigDecimal fCurrentMoney, Date fModifyTime, Integer fModifyUserId) {
        this.id = id;
        this.fCurrentMoney = fCurrentMoney;
        this.fModifyTime = fModifyTime;
        this.fModifyUserId = fModifyUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getfCurrentMoney() {
        return fCurrentMoney;
    }

    public void setfCurrentMoney(BigDecimal fCurrentMoney) {
        this.fCurrentMoney = fCurrentMoney;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfModifyTime() {
        return fModifyTime;
    }

    public void setfModifyTime(Date fModifyTime) {
        this.fModifyTime = fModifyTime;
    }

    public Integer getfModifyUserId() {
        return fModifyUserId;
    }

    public void setfModifyUserId(Integer fModifyUserId) {
        this.fModifyUserId = fModifyUserId;
    }
}
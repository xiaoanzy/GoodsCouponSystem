package com.jhxaa.yhj.pojo;

import java.util.Date;

public class TGoodsInfoProblem {
    private Integer id;

    private Integer fGoodsKeyId;

    private Date fCreateTime;

    private Date fModifyTime;

    private Boolean fIsModify;


    public TGoodsInfoProblem(Integer fGoodsKeyId, Date fCreateTime, Date fModifyTime, Boolean fIsModify) {
        this.fGoodsKeyId = fGoodsKeyId;
        this.fCreateTime = fCreateTime;
        this.fModifyTime = fModifyTime;
        this.fIsModify = fIsModify;
    }

    public TGoodsInfoProblem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfGoodsKeyId() {
        return fGoodsKeyId;
    }

    public void setfGoodsKeyId(Integer fGoodsKeyId) {
        this.fGoodsKeyId = fGoodsKeyId;
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

    public Boolean getfIsModify() {
        return fIsModify;
    }

    public void setfIsModify(Boolean fIsModify) {
        this.fIsModify = fIsModify;
    }
}
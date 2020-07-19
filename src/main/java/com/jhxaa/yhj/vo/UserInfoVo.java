package com.jhxaa.yhj.vo;

import java.util.Date;

public class UserInfoVo {

    private Integer id;

    private Integer fUserUuid;

    private String fUserName;

    private Date fCreateTime;

    private Date fModifyTime;

    private Integer fManageType;

    public UserInfoVo() {
    }

    public UserInfoVo(Integer id, Integer fUserUuid, String fUserName, Date fCreateTime, Date fModifyTime, Integer fManageType) {
        this.id = id;
        this.fUserUuid = fUserUuid;
        this.fUserName = fUserName;
        this.fCreateTime = fCreateTime;
        this.fModifyTime = fModifyTime;
        this.fManageType = fManageType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfUserUuid() {
        return fUserUuid;
    }

    public void setfUserUuid(Integer fUserUuid) {
        this.fUserUuid = fUserUuid;
    }

    public String getfUserName() {
        return fUserName;
    }

    public void setfUserName(String fUserName) {
        this.fUserName = fUserName;
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

    public Integer getfManageType() {
        return fManageType;
    }

    public void setfManageType(Integer fManageType) {
        this.fManageType = fManageType;
    }
}

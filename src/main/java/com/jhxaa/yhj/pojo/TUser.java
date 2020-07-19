package com.jhxaa.yhj.pojo;

import java.util.Date;

public class TUser {
    private Integer id;

    private Integer fUserUuid;

    private String fUserName;

    private String fUserPassword;

    private Date fCreateTime;

    private Date fModifyTime;

    private Integer fManageType;


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
        this.fUserName = fUserName == null ? null : fUserName.trim();
    }

    public String getfUserPassword() {
        return fUserPassword;
    }

    public void setfUserPassword(String fUserPassword) {
        this.fUserPassword = fUserPassword == null ? null : fUserPassword.trim();
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
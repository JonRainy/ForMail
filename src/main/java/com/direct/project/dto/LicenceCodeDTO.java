package com.direct.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class LicenceCodeDTO {

    Integer id;

    String code;

    String deviceCode;

    String userMail;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date updateTime;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

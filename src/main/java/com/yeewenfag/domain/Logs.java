package com.yeewenfag.domain;

import java.util.Date;

public class Logs {
    private Long id;

    private String userId;

    private Date operateTime;

    private String ipAddress;

    private String operateDes;

    private String operateResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getOperateDes() {
        return operateDes;
    }

    public void setOperateDes(String operateDes) {
        this.operateDes = operateDes == null ? null : operateDes.trim();
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }
}
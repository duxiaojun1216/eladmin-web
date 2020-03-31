package me.zhengjie.config.drools;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;
    private Integer status;

    private double money;

    private Double resultMoney;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    private String resultType;

    private int fcType;

    public int getFcType() {
        return fcType;
    }

    public void setFcType(int fcType) {
        this.fcType = fcType;
    }

    public Double getResultMoney() {
        return resultMoney;
    }

    public void setResultMoney(Double resultMoney) {
        this.resultMoney = resultMoney;
    }

    private Date startTime;
    private Date endTime;
    private Date nowTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

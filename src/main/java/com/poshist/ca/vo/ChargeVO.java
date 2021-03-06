package com.poshist.ca.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChargeVO {
    private Long id;
    private Long item;
    private Long otherService;
    private Long incomePlatform;
    private Long incomeType;
    private Long customType;
    private Double incomeValue;
    private String customMobile;
    private String operateTimeStr;
    private String operateTimeStartStr;
    private String getOperateTimeEndStr;
    private Double itemValue;
    private Double itemCount;

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public Double getItemCount() {
        return itemCount;
    }

    public void setItemCount(Double itemCount) {
        this.itemCount = itemCount;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date operateTime;

    public String getOperateTimeStartStr() {
        return operateTimeStartStr;
    }

    public void setOperateTimeStartStr(String operateTimeStartStr) {
        this.operateTimeStartStr = operateTimeStartStr;
    }

    public String getGetOperateTimeEndStr() {
        return getOperateTimeEndStr;
    }

    public void setGetOperateTimeEndStr(String getOperateTimeEndStr) {
        this.getOperateTimeEndStr = getOperateTimeEndStr;
    }

    public String getOperateTimeStr() {
        return operateTimeStr;
    }

    public void setOperateTimeStr(String operateTimeStr) {
        this.operateTimeStr = operateTimeStr;
        String[] str=operateTimeStr.split(" - ");
        this.operateTimeStartStr=str[0];
        this.getOperateTimeEndStr=str[1];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Long getOtherService() {
        return otherService;
    }

    public void setOtherService(Long otherService) {
        this.otherService = otherService;
    }

    public Long getIncomePlatform() {
        return incomePlatform;
    }

    public void setIncomePlatform(Long incomePlatform) {
        this.incomePlatform = incomePlatform;
    }

    public Long getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Long incomeType) {
        this.incomeType = incomeType;
    }

    public Long getCustomType() {
        return customType;
    }

    public void setCustomType(Long customType) {
        this.customType = customType;
    }

    public Double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getCustomMobile() {
        return customMobile;
    }

    public void setCustomMobile(String customMobile) {
        this.customMobile = customMobile;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public void init(){
        this.incomeValue=0d;
        this.operateTime=new Date();
        this.itemCount=0d;
        this.itemValue=0d;
        Calendar ca=Calendar.getInstance();
        ca.add(Calendar.MONTH,-1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       this.operateTimeStartStr =df.format(ca.getTime());
       this.getOperateTimeEndStr=df.format(operateTime);

    }

}

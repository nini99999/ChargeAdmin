package com.poshist.ca.entity;

import com.poshist.common.AbstractEntity;
import com.poshist.sys.entity.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_ca_pay_info")
@EntityListeners(AuditingEntityListener.class)
public class PayInfo extends AbstractEntity {
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    private ItemInfo itemInfo;
    private Double incomeValue;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "income_type",referencedColumnName = "id")
    private DictionaryInfo incomeType;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "income_platform",referencedColumnName = "id")
    private DictionaryInfo incomePlatform;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "custom_type",referencedColumnName = "id")
    private DictionaryInfo customType;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "other_service",referencedColumnName = "id")
    private DictionaryInfo otherService;
    private String customMobile;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "operate_id",referencedColumnName = "id")
    private User operate;
    private Date operateTime;
    private Date createTime;
    private Integer status;

    public DictionaryInfo getOtherService() {
        return otherService;
    }

    public void setOtherService(DictionaryInfo otherService) {
        this.otherService = otherService;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getOperate() {
        return operate;
    }

    public void setOperate(User operate) {
        this.operate = operate;
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public Double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public DictionaryInfo getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(DictionaryInfo incomeType) {
        this.incomeType = incomeType;
    }

    public DictionaryInfo getIncomePlatform() {
        return incomePlatform;
    }

    public void setIncomePlatform(DictionaryInfo incomePlatform) {
        this.incomePlatform = incomePlatform;
    }

    public DictionaryInfo getCustomType() {
        return customType;
    }

    public void setCustomType(DictionaryInfo customType) {
        this.customType = customType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

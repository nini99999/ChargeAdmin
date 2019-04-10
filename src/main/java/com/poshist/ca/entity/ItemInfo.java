package com.poshist.ca.entity;

import com.poshist.common.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_ca_item_info")
@EntityListeners(AuditingEntityListener.class)
public class ItemInfo extends AbstractEntity {
    private String name;
    private Integer status;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private ItemInfo parentInfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentInfo", fetch = FetchType.EAGER)
    @OrderBy("id")
    private Set<ItemInfo> childrenInfo;
    private Double itemValue;

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
       this.itemValue = itemValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ItemInfo getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(ItemInfo parentInfo) {
        this.parentInfo = parentInfo;
    }

    public Set<ItemInfo> getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(Set<ItemInfo> childrenInfo) {
        this.childrenInfo = childrenInfo;
    }
}


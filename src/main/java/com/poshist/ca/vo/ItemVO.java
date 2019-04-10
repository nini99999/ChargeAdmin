package com.poshist.ca.vo;

import com.poshist.ca.entity.ItemInfo;

public class ItemVO {
    private Long id;
    private String name;
    private Long parentId;
    private Double itemValue;
    public ItemVO(){}
    public ItemVO(ItemInfo itemInfo){
        this.id=itemInfo.getId();
        this.name=itemInfo.getName();
        if(null!=itemInfo.getParentInfo()){
            this.parentId= itemInfo.getParentInfo().getId();
        }
        this.itemValue=itemInfo.getItemValue();

    }
   public ItemInfo toItemInfo(){
        ItemInfo itemInfo=new ItemInfo();
        itemInfo.setId(this.id);
        itemInfo.setName(this.name);
        itemInfo.setItemValue(this.itemValue);
        return itemInfo;
   }
    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

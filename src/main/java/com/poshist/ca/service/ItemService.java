package com.poshist.ca.service;

import com.poshist.ca.entity.ItemInfo;
import com.poshist.ca.repository.ItemInfoDao;
import com.poshist.ca.vo.ItemVO;
import com.poshist.ca.vo.SelectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemInfoDao itemInfoDao;
    public ItemVO saveItem(ItemVO itemVO){
        Optional<ItemInfo> itemInfoOptional=itemInfoDao.findById(itemVO.getId());
        if(itemInfoOptional.isPresent()){
         ItemInfo itemInfo=itemVO.toItemInfo(itemInfoOptional.get());
         itemInfoDao.save(itemInfo);
        }
       return itemVO;
    }
    public ItemVO delItem(Long id){
        Optional<ItemInfo> itemInfoOptional=itemInfoDao.findById(id);
        if(itemInfoOptional.isPresent()){
            ItemInfo itemInfo=itemInfoOptional.get();
            itemInfo.setStatus(1);
            itemInfoDao.save(itemInfo);
            return new ItemVO(itemInfo);
        }
        return null;
    }
    public List<ItemVO>getItemList(Long parentId){
        ItemInfo itemInfo=itemInfoDao.findById(parentId).get();
        List<ItemVO> itemVOs=new ArrayList<>();
        for(ItemInfo childrenInfo:itemInfo.getChildrenInfo()){
            if(childrenInfo.getStatus()==0){
                ItemVO itemVO=new ItemVO(childrenInfo);
                itemVOs.add(itemVO);
            }
        }
        return itemVOs;
    }
    public ItemVO addItem(ItemVO itemVO){
         ItemInfo itemInfo=itemVO.toItemInfo(new ItemInfo());
         itemInfo.setStatus(0);
        Optional<ItemInfo> getItemInfo=itemInfoDao.findById(itemVO.getParentId());
        if(getItemInfo.isPresent()){
           itemInfo.setParentInfo(getItemInfo.get());
        }
        itemInfoDao.save(itemInfo);
        itemVO.setId(itemInfo.getId());
        return itemVO;
    }
    public ItemVO getItemInfo(Long id){
        Optional<ItemInfo> getItemInfo=itemInfoDao.findById(id);
        if(getItemInfo.isPresent()){
            return new ItemVO(getItemInfo.get());
        }
        return null;
    }

    public List<SelectVO> getItemInfoSelect(Long type){
        List<ItemInfo> itemList=itemInfoDao.getAllByIdAndStatus(type,0);

        return analysisItem(itemList);
    }
    private List<SelectVO> analysisItem(List<ItemInfo> itemList){
        List<SelectVO> list=new ArrayList<SelectVO>();
        if(null==itemList||itemList.isEmpty()){
            return list;
        }
        for(ItemInfo itemInfo:itemList.get(0).getChildrenInfo()){
            if(itemInfo.getStatus()==0) {
                SelectVO vo = new SelectVO();
                vo.setName(itemInfo.getName());
                vo.setValue(itemInfo.getId());
                vo.setMemo(itemInfo.getItemValue().toString());
                list.add(vo);
                for (ItemInfo chilren : itemInfo.getChildrenInfo()) {
                    if(chilren.getStatus()==0) {
                        vo = new SelectVO();
                        vo.setName("----" + chilren.getName());
                        vo.setValue(chilren.getId());
                        vo.setMemo(chilren.getItemValue().toString());
                        list.add(vo);
                    }
                }
            }
        }
        return list;

    }
}

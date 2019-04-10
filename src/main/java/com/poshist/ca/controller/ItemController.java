package com.poshist.ca.controller;

import com.poshist.ca.service.ItemService;
import com.poshist.ca.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/itemList")
    public String itemList(Long parentId,Integer type, Model model){
        ItemVO itemInfo=itemService.getItemInfo(parentId);
        List  itemInfos=itemService.getItemList(parentId);
        model.addAttribute("itemInfos",itemInfos);
        model.addAttribute("itemInfo",itemInfo);
        model.addAttribute("type",type);
      return "item/itemList";
    }
    @RequestMapping("/addItemInit")
    public String addItemInit (Long parentId, Model model){
        ItemVO itemInfo=itemService.getItemInfo(parentId);
        model.addAttribute("parent",itemInfo);
        model.addAttribute("itemInfo",new ItemVO());
    return "item/itemInfo";
    }
    @RequestMapping("/addItem")
    public String addItem(ItemVO itemVO,Model model){
        itemService.addItem(itemVO);
//        Long parentId=null;
//        if(itemVO.getParentId()==1||itemVO.getParentId()==2){
//            parentId=itemVO.getParentId();
//        }else{
//            ItemVO parent=itemService.getItemInfo(itemVO.getParentId());
//            parentId=parent.getParentId();
//        }
        return "redirect:itemList?parentId="+itemVO.getParentId();
    }
    @RequestMapping("/delItem")
    public String delItem(Long id){
       ItemVO itemVO= itemService.delItem(id);
        return "redirect:itemList?parentId="+itemVO.getParentId();
    }

}

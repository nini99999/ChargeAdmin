package com.poshist.ca.controller;

import com.poshist.ca.entity.IncomeInfo;
import com.poshist.ca.service.CAService;
import com.poshist.ca.vo.ChargeVO;
import com.poshist.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ca")
public class CAController {
    @Autowired
    private CAService caService;
    @RequestMapping("/incomeList")
    public String incomeList(Model model){
        Page<IncomeInfo> page=caService.getIncomeList();
        model.addAttribute("incomeList",page.getContent());
        return "ca/incomeList";
    }
    @RequestMapping("/addIncomeInit")
    public String addIncomeInit(Model model){
        List incomeType=caService.getDictionaryInfoListByType(Constant.INCOMETYPE);
        List incomePlatform=caService.getDictionaryInfoListByType(Constant.INCOMEPLATFORM);
        List otherService=caService.getDictionaryInfoListByType(Constant.OTHERSERVICE);
        List customType=caService.getDictionaryInfoListByType(Constant.CUSTOMTYPE);
        List itemInfo=caService.getItemInfo(Constant.TYPEINCOME);
        model.addAttribute("incomeType",incomeType);
        model.addAttribute("incomePlatform",incomePlatform);
        model.addAttribute("otherService",otherService);
        model.addAttribute("customType",customType);
        model.addAttribute("itemInfo",itemInfo);
        return "ca/addIncome";
    }
    @RequestMapping("/addIncome")
    public String addIncome(ChargeVO chargeVO){
        caService.saveIncomeInfo(chargeVO);
        return "redirect:addIncomeInit";
    }


}

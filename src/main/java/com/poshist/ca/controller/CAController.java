package com.poshist.ca.controller;

import com.poshist.ca.entity.IncomeInfo;
import com.poshist.ca.service.CAService;
import com.poshist.ca.vo.ChargeVO;
import com.poshist.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ca")
public class CAController {
    @Autowired
    private CAService caService;
    @RequestMapping("/incomeList")
    public String incomeList(Model model, HttpServletRequest request){
//        Integer pageNumber=null;
//        if(null==request.getParameter("pageNumber")){
//             pageNumber =1;
//        }else {
//             pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
//        }[[${session.loginUser.user.realName}]]
//        Page<IncomeInfo> page=caService.getIncomeList(pageNumber);
//        model.addAttribute("incomeList",page.getContent());
//        model.addAttribute("page",page);
//        page.getPageable().getPageNumber()
       List <IncomeInfo> list=caService.getIncomeList();
        model.addAttribute("incomeList",list);
        return "ca/incomeList";
    }
    @RequestMapping("/addIncomeInit")
    public String addIncomeInit(@ModelAttribute(value="incomeInfo")IncomeInfo  incomeInfo, Model model){
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
        model.addAttribute("incomeInfo",incomeInfo);
        return "ca/incomeInfo";
    }
    @RequestMapping("/addIncome")
    public String addIncome(ChargeVO chargeVO){
        caService.saveIncomeInfo(chargeVO);
        return "redirect:addIncomeInit";
    }
    @RequestMapping("/delIncome")
    public String delIncome(HttpServletRequest request){
        caService.delIncome(Long.valueOf(request.getParameter("id")));
        return "redirect:incomeList";
    }
    @RequestMapping("/editIncomeInit")
    public String editIncomeInit(HttpServletRequest request, RedirectAttributes attr){
       IncomeInfo incomeInfo=caService.getIncomeInfo(Long.valueOf(request.getParameter("id")));
        attr.addFlashAttribute("incomeInfo",incomeInfo);
        return "redirect:addIncomeInit";
    }

}

package com.poshist.ca.controller;

import com.poshist.ca.entity.IncomeInfo;
import com.poshist.ca.entity.PayInfo;
import com.poshist.ca.service.CAService;
import com.poshist.ca.service.ItemService;
import com.poshist.ca.vo.ChargeVO;
import com.poshist.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/ca")
public class CAController {
    @Autowired
    private CAService caService;
    @Autowired
    private ItemService itemService;
    @RequestMapping("/payExcel")
    public void payExcel(ChargeVO chargeVO, HttpServletResponse response) throws IOException {

        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("付款记录.xls".getBytes("UTF-8"), "ISO-8859-1") );
        response.setContentType("application/octet-stream");
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        caService.getPayExcel(chargeVO,out);
        out.flush();
        out.close();

    }
    @RequestMapping("/incomeExcel")
    public void incomeExcel(ChargeVO chargeVO, HttpServletResponse response) throws IOException {

        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("收款记录.xls".getBytes("UTF-8"), "ISO-8859-1") );
        response.setContentType("application/octet-stream");
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        caService.getIncomeExcel(chargeVO,out);
        out.flush();
        out.close();

    }
    @RequestMapping("/payList")
    public String payList(Model model, ChargeVO chargeVO) {
        if (null == chargeVO.getOperateTimeStr()) {
            chargeVO = new ChargeVO();
            chargeVO.init();
        }
        List<PayInfo> list = caService.getPayList(chargeVO);
        List incomeType = caService.getDictionaryInfoListByType(Constant.INCOMETYPE);
        List itemInfo = itemService.getItemInfoSelect(Constant.TYPEBUY);
        model.addAttribute("incomeType", incomeType);
        model.addAttribute("itemInfo", itemInfo);

        model.addAttribute("chargeVO", chargeVO);
        model.addAttribute("payList", list);
        return "ca/payList";
    }

    @RequestMapping("/incomeList")
    public String incomeList(Model model, ChargeVO chargeVO) {
        if (null == chargeVO.getOperateTimeStr()) {
            chargeVO = new ChargeVO();
            chargeVO.init();
        }
        List<IncomeInfo> list = caService.getIncomeList(chargeVO);
        List incomeType = caService.getDictionaryInfoListByType(Constant.INCOMETYPE);
        List itemInfo = itemService.getItemInfoSelect(Constant.TYPEINCOME);
        model.addAttribute("incomeType", incomeType);
        model.addAttribute("itemInfo", itemInfo);

        model.addAttribute("chargeVO", chargeVO);
        model.addAttribute("payList", list);

        model.addAttribute("incomeList", list);
        return "ca/incomeList";
    }

    @RequestMapping("/addPayInit")
    public String addPayInit(@ModelAttribute(value = "chargeVO") ChargeVO chargeVO, Model model) {
        List payType = caService.getDictionaryInfoListByType(Constant.INCOMETYPE);
        List payPlatform = caService.getDictionaryInfoListByType(Constant.INCOMEPLATFORM);
        List otherService = caService.getDictionaryInfoListByType(Constant.OTHERSERVICE);
        List customType = caService.getDictionaryInfoListByType(Constant.CUSTOMTYPE);
        List itemInfo = itemService.getItemInfoSelect(Constant.TYPEBUY);
        model.addAttribute("payType", payType);
        model.addAttribute("payPlatform", payPlatform);
        model.addAttribute("otherService", otherService);
        model.addAttribute("customType", customType);
        model.addAttribute("itemInfo", itemInfo);
        if (null == chargeVO || null == chargeVO.getId()) {
            chargeVO = new ChargeVO();
            chargeVO.init();
        }
        model.addAttribute("chargeVO", chargeVO);
        return "ca/payInfo";
    }

    @RequestMapping("/addIncomeInit")
    public String addIncomeInit(@ModelAttribute(value = "chargeVO") ChargeVO chargeVO, Model model) {
        List incomeType = caService.getDictionaryInfoListByType(Constant.INCOMETYPE);
        List incomePlatform = caService.getDictionaryInfoListByType(Constant.INCOMEPLATFORM);
        List otherService = caService.getDictionaryInfoListByType(Constant.OTHERSERVICE);
        List customType = caService.getDictionaryInfoListByType(Constant.CUSTOMTYPE);
        List itemInfo = itemService.getItemInfoSelect(Constant.TYPEINCOME);
        model.addAttribute("incomeType", incomeType);
        model.addAttribute("incomePlatform", incomePlatform);
        model.addAttribute("otherService", otherService);
        model.addAttribute("customType", customType);
        model.addAttribute("itemInfo", itemInfo);
        if (null == chargeVO || null == chargeVO.getId()) {
            chargeVO = new ChargeVO();
            chargeVO.init();
        }
        model.addAttribute("chargeVO", chargeVO);
        return "ca/incomeInfo";
    }

    @RequestMapping("/addPay")
    public String addPay(ChargeVO chargeVO) {
        caService.addPayInfo(chargeVO);
        return "redirect:addIncomeInit";
    }

    @RequestMapping("/addIncome")
    public String addIncome(ChargeVO chargeVO) {
        caService.addIncomeInfo(chargeVO);
        return "redirect:addIncomeInit";
    }

    @RequestMapping("/savePay")
    public String savePay(ChargeVO chargeVO) {
        caService.savePayInfo(chargeVO);
        return "redirect:payList";
    }

    @RequestMapping("/saveIncome")
    public String saveIncome(ChargeVO chargeVO) {
        caService.saveIncomeInfo(chargeVO);
        return "redirect:incomeList";
    }

    @RequestMapping("/delPay")
    public String delPay(HttpServletRequest request) {
        caService.delPay(Long.valueOf(request.getParameter("id")));
        return "redirect:payList";
    }

    @RequestMapping("/delIncome")
    public String delIncome(HttpServletRequest request) {
        caService.delIncome(Long.valueOf(request.getParameter("id")));
        return "redirect:incomeList";
    }

    @RequestMapping("/editPayInit")
    public String editPayInit(HttpServletRequest request, RedirectAttributes attr) {
        ChargeVO chargeVO = caService.getPayInfo(Long.valueOf(request.getParameter("id")));
        attr.addFlashAttribute("chargeVO", chargeVO);
        return "redirect:addPayInit";
    }

    @RequestMapping("/editIncomeInit")
    public String editIncomeInit(HttpServletRequest request, RedirectAttributes attr) {
        ChargeVO chargeVO = caService.getIncomeInfo(Long.valueOf(request.getParameter("id")));
        attr.addFlashAttribute("chargeVO", chargeVO);
        return "redirect:addIncomeInit";
    }

}

package com.poshist.ca.controller;

import com.poshist.ca.service.BIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bi")
public class BIController {
    @Autowired
    private BIService biService;

    @RequestMapping("/mainReport")
    public String mainReport(Model model){
        String [] lastTenPay=biService.getLastPay(10);
        String [] lastTenIncome=biService.getLastIncome(10);
        Double todayPay=biService.getTodayPay();
        Double todayIncome=biService.getTodayPay();
        Double monthPay=biService.getMonthPay();
        Double monthIncome=biService.getMonthIncome();
        model.addAttribute("lastTenPay",lastTenPay);
        model.addAttribute("lastTenIncome",lastTenIncome);
        model.addAttribute("todayPay",todayPay);
        model.addAttribute("todayIncome",todayIncome);
        model.addAttribute("monthPay",monthPay);
        model.addAttribute("monthIncome",monthIncome);
        return "bi/main";
    }
}

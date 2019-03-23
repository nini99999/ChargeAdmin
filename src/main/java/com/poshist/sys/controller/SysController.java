package com.poshist.sys.controller;

import com.poshist.sys.entity.User;
import com.poshist.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sys")
public class SysController {
    @Autowired
    UserService userService;
    @RequestMapping("/userList")
    public String userList(Model model){
        List<User> userList=userService.getAllUser();
        model.addAttribute("userList",userList);
        return "sys/userList";
    }
    @RequestMapping("/changeUserStatus")
    public String changeUserStatus(Long id){
        userService.changeUserStatus(id);
        return "redirect:userList";
    }
}

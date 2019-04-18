package com.poshist.sys.controller;

import com.poshist.sys.entity.Role;
import com.poshist.sys.entity.User;
import com.poshist.sys.service.UserService;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/getUserByName")
    @ResponseBody
    public String getUserByName(String userName){
        UserDetails user=userService.getUserByName(userName);
        if(null==user){
            return "{\"valid\":true}";
        }
        return "{\"valid\":false}";

    }
    @RequestMapping("/isPassword")
    @ResponseBody
    public String isPassword(String oldPassword){
        if(userService.isPassword(oldPassword)){
            return "{\"valid\":true}";
        }
        return "{\"valid\":false}";
    }
    @RequestMapping("/changePasswordInit")
    public String changePasswordInit(){
        return "sys/changePassword";
    }
    @RequestMapping("/changePassword")
    public String changePassword(String password){
        userService.changePassword(password);
        return "sys/changePassword";
    }
    @RequestMapping("/changeUserStatus")
    public String changeUserStatus(Long id){
        userService.changeUserStatus(id);
        return "redirect:userList";
    }
    @RequestMapping("/addUserInit")
    public String addUserInit(Model model ){
        List<Role> roles=userService.getAllRole();

        model.addAttribute("roles",roles);
        return "sys/userInfo";
    }
    @RequestMapping("/addUser")
    public String addUser(UserVO userVO ){
        userService.addUser(userVO);
        return "redirect:userList";
    }
}

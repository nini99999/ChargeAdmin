package com.poshist.sys.service;

import com.poshist.common.Constant;
import com.poshist.sys.entity.Role;
import com.poshist.sys.entity.User;
import com.poshist.sys.repository.RoleDao;
import com.poshist.sys.repository.UserDao;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    public UserDetails getUserByName(String userName){
        User user=userDao.findUserByUserNameAndStatus(userName, Constant.VALID);
        if(null!=user) {
            UserVO userVO = new UserVO();
            userVO.setUser(user);
            return userVO;
        }
        return null;
    }
    public List<User> getAllUser(){
        return userDao.findAll();
    }
    public List<Role> getAllRole(){
        return roleDao.getAllByStatus("0");
    }
    public User changeUserStatus(Long  id){
        User user=userDao.findById(id).get();
        if("0".equals(user.getStatus())){
            user.setStatus("1");
        }else{
            user.setStatus("0");
        }
        userDao.save(user);
        return user;
    }
}

package com.poshist.sys.service;

import com.poshist.common.Constant;
import com.poshist.sys.entity.Role;
import com.poshist.sys.entity.User;
import com.poshist.sys.repository.RoleDao;
import com.poshist.sys.repository.UserDao;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            return dtoToVo(user);
        }
        return null;
    }
    public UserDetails addUser(UserVO userVO){
        User user=voToDto(userVO);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setPassword(encode.encode(userVO.getPassword()));

        return  dtoToVo(user);
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
    private UserVO dtoToVo(User user){
        UserVO userVO=new UserVO();
        userVO.setId(user.getId());
        userVO.setUserName(user.getUserName());
        userVO.setPassword(user.getPassword());
        userVO.setRealName(user.getRealName());
        userVO.setMobile(user.getMobile());
        userVO.setEmail(user.getEmail());
        return userVO;
    }
    private User voToDto(UserVO userVO){
        User user=new User();
        user.setId(userVO.getId());
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setRealName(userVO.getRealName());
        user.setMobile(userVO.getMobile());
        user.setEmail(userVO.getMobile());
        return user;
    }
}

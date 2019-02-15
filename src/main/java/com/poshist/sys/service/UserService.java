package com.poshist.sys.service;

import com.poshist.common.Constant;
import com.poshist.sys.entity.User;
import com.poshist.sys.repository.UserDao;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    public UserDetails getUserByName(String userName){
        User user=userDao.findUserByUserNameAndStatus(userName, Constant.VALID);
        if(null!=user) {
            UserVO userVO = new UserVO();
            userVO.setUser(user);
            return userVO;
        }
        return null;
    }
}

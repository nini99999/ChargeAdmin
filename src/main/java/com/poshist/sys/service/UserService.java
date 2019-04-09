package com.poshist.sys.service;

import com.poshist.common.Constant;
import com.poshist.sys.entity.Role;
import com.poshist.sys.entity.RoleFunction;
import com.poshist.sys.entity.User;
import com.poshist.sys.entity.UserRole;
import com.poshist.sys.repository.RoleDao;
import com.poshist.sys.repository.UserDao;
import com.poshist.sys.vo.FunctionVO;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            UserVO userVO= dtoToVo(user);
            userVO.setFunctions(analysisFunction(user.getUserRoles().get(0).getRole()));
            return userVO;
        }

        return null;
    }
    public UserDetails addUser(UserVO userVO){
        User user=voToDto(userVO);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setPassword(encode.encode(userVO.getPassword()));
        Role role =roleDao.findById(userVO.getRoleId()).get();
        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        List<UserRole> userRoles=new ArrayList<UserRole>();
        userRoles.add(userRole);
        user.setStatus("0");
        user.setUserRoles(userRoles);
        userDao.save(user);
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
      user.setEmail(userVO.getEmail());
        return user;
    }
    private List<FunctionVO> analysisFunction(Role role){
        List<FunctionVO> functionVOS=new ArrayList<FunctionVO>();
        //构造一级菜单
        for (RoleFunction roleFunction:role.getRoleFunctions()){
            if(roleFunction.getFunction().getId()!=0&&roleFunction.getFunction().getParentFunction().getId()==0){
                FunctionVO functionVO=new FunctionVO(roleFunction.getFunction());
                functionVOS.add(functionVO);
            }
        }
        //构造二级菜单
        for (RoleFunction roleFunction:role.getRoleFunctions()){
            if(roleFunction.getFunction().getId()!=0&&roleFunction.getFunction().getParentFunction().getId()!=0){
                for(FunctionVO parent:functionVOS){
                    if(parent.getId()==roleFunction.getFunction().getParentFunction().getId()){
                        FunctionVO functionVO=new FunctionVO(roleFunction.getFunction());
                        parent.addChild(functionVO);
                    }
                }
            }
        }
        return  functionVOS;
    }
}

package com.poshist.sys.repository;

import com.poshist.sys.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findUserByUserNameAndStatus(String userName,String status);
}

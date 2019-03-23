package com.poshist.sys.repository;

import com.poshist.sys.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDao extends CrudRepository<Role, Long> {
    public List<Role> getAllByStatus(String status);
}

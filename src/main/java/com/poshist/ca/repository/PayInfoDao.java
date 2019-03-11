package com.poshist.ca.repository;

import com.poshist.ca.entity.PayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PayInfoDao extends CrudRepository<PayInfo, Long> {
    public Page<PayInfo> getAllByStatusOrderByIdDesc(Integer status, Pageable pageable);
    public List<PayInfo> getAllByStatusOrderByIdDesc(Integer status);
}

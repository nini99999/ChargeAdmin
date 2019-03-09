package com.poshist.ca.repository;

import com.poshist.ca.entity.IncomeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomeInfoDao extends CrudRepository<IncomeInfo, Long> {
    public Page<IncomeInfo> getAllByStatusOrderByIdDesc(Integer status, Pageable pageable);
    public List<IncomeInfo> getAllByStatusOrderByIdDesc(Integer status);
}

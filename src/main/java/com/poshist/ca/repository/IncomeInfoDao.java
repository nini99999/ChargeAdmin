package com.poshist.ca.repository;

import com.poshist.ca.entity.IncomeInfo;
import com.poshist.ca.entity.PayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomeInfoDao extends CrudRepository<IncomeInfo, Long>  , JpaSpecificationExecutor<IncomeInfo> {
    @Query(value = "select sum(incomeValue) from IncomeInfo where status=?1 and    date_format(operate_time,'%Y-%m-%d')>=?2 and date_format(operate_time,'%Y-%m-%d')<=?3")
    public Double getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(Integer status, String after, String before);
}

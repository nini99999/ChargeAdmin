package com.poshist.ca.repository;

import com.poshist.ca.entity.PayInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PayInfoDao extends CrudRepository<PayInfo, Long> , JpaSpecificationExecutor<PayInfo> {
    @Query(value = "select sum(incomeValue) from PayInfo where status=?1 and    date_format(operate_time,'%Y-%m-%d')>=?2 and date_format(operate_time,'%Y-%m-%d')<=?3")
    public Double getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(Integer status, String after, String before);
}

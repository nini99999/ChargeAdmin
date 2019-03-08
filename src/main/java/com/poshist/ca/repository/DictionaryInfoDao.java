package com.poshist.ca.repository;

import com.poshist.ca.entity.DictionaryInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DictionaryInfoDao extends CrudRepository<DictionaryInfo, Long> {
    public List<DictionaryInfo> getAllByStatusAndTypeOrderById(Integer status, String type);
}

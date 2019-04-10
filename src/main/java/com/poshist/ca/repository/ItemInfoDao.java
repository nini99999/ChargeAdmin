package com.poshist.ca.repository;

import com.poshist.ca.entity.ItemInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemInfoDao extends CrudRepository<ItemInfo, Long> {
public List<ItemInfo>getAllByIdAndStatus(Long id,Integer status);

}

package com.poshist.ca.service;

import com.poshist.ca.entity.DictionaryInfo;
import com.poshist.ca.repository.DictionaryInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryInfoDao dictionaryInfoDao;
    /**
     *  获取字典表
     * @param type
     * @return
     */
    public List<DictionaryInfo> getDictionaryInfoListByType(String type ){
        return dictionaryInfoDao.getAllByStatusAndTypeOrderById(0,type);
    }

}

package com.poshist.ca.service;

import com.poshist.ca.entity.DictionaryInfo;
import com.poshist.ca.entity.IncomeInfo;
import com.poshist.ca.entity.ItemInfo;
import com.poshist.ca.entity.PayInfo;
import com.poshist.ca.repository.DictionaryInfoDao;
import com.poshist.ca.repository.IncomeInfoDao;
import com.poshist.ca.repository.ItemInfoDao;
import com.poshist.ca.repository.PayInfoDao;
import com.poshist.ca.vo.ChargeVO;
import com.poshist.ca.vo.SelectVO;
import com.poshist.common.Constant;
import com.poshist.sys.repository.UserDao;
import com.poshist.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CAService {
    @Autowired
    private IncomeInfoDao incomeInfoDao;
    @Autowired
    private DictionaryInfoDao dictionaryInfoDao;
    @Autowired
    private ItemInfoDao itemInfoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PayInfoDao payInfoDao;
    public Page<IncomeInfo> getIncomeList(Integer pageNumber){
        Pageable pageable = new PageRequest(pageNumber-1, Constant.PAGESIZE);
        return incomeInfoDao.getAllByStatusOrderByIdDesc(0,pageable);
    }

    public List<PayInfo> getPayList(){
        return payInfoDao.getAllByStatusOrderByIdDesc(0);
    }
    public List<IncomeInfo> getIncomeList(){
        return incomeInfoDao.getAllByStatusOrderByIdDesc(0);
    }
    /**
     *  获取字典表
     * @param type
     * @return
     */
    public List<DictionaryInfo> getDictionaryInfoListByType(String type ){
        return dictionaryInfoDao.getAllByStatusAndTypeOrderById(0,type);
    }
    public List<SelectVO>getItemInfo(Long type){
     List<ItemInfo> itemList=itemInfoDao.getAllByIdAndStatus(type,0);

     return analysisItem(itemList);
    }
    public ChargeVO getPayInfo(Long id){
        PayInfo payInfo= payInfoDao.findById(id).get();
        ChargeVO chargeVO=new ChargeVO();
        chargeVO.setCustomMobile(payInfo.getCustomMobile());
        chargeVO.setCustomType(payInfo.getCustomType().getId());
        chargeVO.setIncomePlatform(payInfo.getIncomePlatform().getId());
        chargeVO.setIncomeType(payInfo.getIncomeType().getId());
        chargeVO.setIncomeValue(payInfo.getIncomeValue());
        chargeVO.setItem(payInfo.getItemInfo().getId());
        chargeVO.setOperateTime(payInfo.getOperateTime());
        if(null!=payInfo.getOtherService()) {
            chargeVO.setOtherService(payInfo.getOtherService().getId());
        }
        chargeVO.setId(payInfo.getId());
        return chargeVO;
    }

    public ChargeVO getIncomeInfo(Long id){
        IncomeInfo incomeInfo= incomeInfoDao.findById(id).get();
        ChargeVO chargeVO=new ChargeVO();
        chargeVO.setCustomMobile(incomeInfo.getCustomMobile());
        chargeVO.setCustomType(incomeInfo.getCustomType().getId());
        chargeVO.setIncomePlatform(incomeInfo.getIncomePlatform().getId());
        chargeVO.setIncomeType(incomeInfo.getIncomeType().getId());
        chargeVO.setIncomeValue(incomeInfo.getIncomeValue());
        chargeVO.setItem(incomeInfo.getItemInfo().getId());
        chargeVO.setOperateTime(incomeInfo.getOperateTime());
        if(null!=incomeInfo.getOtherService()) {
            chargeVO.setOtherService(incomeInfo.getOtherService().getId());
        }
        chargeVO.setId(incomeInfo.getId());
        return chargeVO;
    }
    private List<SelectVO> analysisItem(List<ItemInfo> itemList){
         List<SelectVO> list=new ArrayList<SelectVO>();
        if(null==itemList||itemList.isEmpty()){
            return list;
        }
        for(ItemInfo itemInfo:itemList.get(0).getChildrenInfo()){
            if(itemInfo.getStatus()==0) {
                SelectVO vo = new SelectVO();
                vo.setName(itemInfo.getName());
                vo.setValue(itemInfo.getId());
                list.add(vo);
                for (ItemInfo chilren : itemInfo.getChildrenInfo()) {
                    if(chilren.getStatus()==0) {
                        vo = new SelectVO();
                        vo.setName("----" + chilren.getName());
                        vo.setValue(chilren.getId());
                        list.add(vo);
                    }
                }
            }
        }
        return list;

    }

    public PayInfo addPayInfo(ChargeVO chargeVO){
        PayInfo payInfo=new PayInfo();
        payInfo=  VOTopayInfo(payInfo,chargeVO);
        payInfo.setCreateTime(new Date());
        payInfo.setStatus(0);
        UserVO principal = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        payInfo.setOperate(userDao.findById(principal.getUser().getId()).get());
        payInfoDao.save(payInfo);
        return payInfo;
    }

    public IncomeInfo addIncomeInfo(ChargeVO chargeVO){
        IncomeInfo incomeInfo=new IncomeInfo();
        incomeInfo=  VOToIncomeInfo(incomeInfo,chargeVO);
        incomeInfo.setCreateTime(new Date());
        incomeInfo.setStatus(0);
        UserVO principal = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        incomeInfo.setOperate(userDao.findById(principal.getUser().getId()).get());
        incomeInfoDao.save(incomeInfo);

        return incomeInfo;
    }

    public PayInfo savePayInfo(ChargeVO chargeVO){
        PayInfo payInfo=payInfoDao.findById(chargeVO.getId()).get();
        payInfo= VOTopayInfo(payInfo,chargeVO);
        payInfoDao.save(payInfo);
        return payInfo;
    }

    public IncomeInfo saveIncomeInfo(ChargeVO chargeVO){
        IncomeInfo incomeInfo=incomeInfoDao.findById(chargeVO.getId()).get();
        incomeInfo= VOToIncomeInfo(incomeInfo,chargeVO);
        incomeInfoDao.save(incomeInfo);
        return incomeInfo;
    }


    private PayInfo VOTopayInfo(PayInfo payInfo , ChargeVO chargeVO){

        ItemInfo itemInfo=itemInfoDao.findById(chargeVO.getItem()).get();
        payInfo.setItemInfo(itemInfo);

        if(null!=chargeVO.getOtherService()) {
            DictionaryInfo otherService = dictionaryInfoDao.findById(chargeVO.getOtherService()).get();
            payInfo.setOtherService(otherService);
        }
        DictionaryInfo incomePlatform= dictionaryInfoDao.findById(chargeVO.getIncomePlatform()).get();
        payInfo.setIncomePlatform(incomePlatform);

        DictionaryInfo incomeType=dictionaryInfoDao.findById(chargeVO.getIncomeType()).get();
        payInfo.setIncomeType(incomeType);

        DictionaryInfo customType=dictionaryInfoDao.findById(chargeVO.getCustomType()).get();
        payInfo.setCustomType(customType);


        payInfo.setId(chargeVO.getId());
        payInfo.setIncomeValue(chargeVO.getIncomeValue());
        payInfo.setCustomMobile(chargeVO.getCustomMobile());
        payInfo.setOperateTime(chargeVO.getOperateTime());
        return payInfo;
    }

    private IncomeInfo VOToIncomeInfo(IncomeInfo incomeInfo , ChargeVO chargeVO){

        ItemInfo itemInfo=itemInfoDao.findById(chargeVO.getItem()).get();
        incomeInfo.setItemInfo(itemInfo);

        if(null!=chargeVO.getOtherService()) {
            DictionaryInfo otherService = dictionaryInfoDao.findById(chargeVO.getOtherService()).get();
            incomeInfo.setOtherService(otherService);
        }
        DictionaryInfo incomePlatform= dictionaryInfoDao.findById(chargeVO.getIncomePlatform()).get();
        incomeInfo.setIncomePlatform(incomePlatform);

        DictionaryInfo incomeType=dictionaryInfoDao.findById(chargeVO.getIncomeType()).get();
        incomeInfo.setIncomeType(incomeType);

        DictionaryInfo customType=dictionaryInfoDao.findById(chargeVO.getCustomType()).get();
        incomeInfo.setCustomType(customType);


        incomeInfo.setId(chargeVO.getId());
        incomeInfo.setIncomeValue(chargeVO.getIncomeValue());
        incomeInfo.setCustomMobile(chargeVO.getCustomMobile());
        incomeInfo.setOperateTime(chargeVO.getOperateTime());
        return incomeInfo;
    }

    public void delPay(Long id){
        PayInfo payInfo=payInfoDao.findById(id).get();
        payInfo.setStatus(1);
        payInfoDao.save(payInfo);
    }
    public void delIncome(Long id){
        IncomeInfo incomeInfo=incomeInfoDao.findById(id).get();
        incomeInfo.setStatus(1);
        incomeInfoDao.save(incomeInfo);
    }

}

package com.poshist.ca.service;

import com.poshist.ca.repository.IncomeInfoDao;
import com.poshist.ca.repository.PayInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class BIService {
    @Autowired
    private PayInfoDao payInfoDao;
    @Autowired
    private IncomeInfoDao incomeInfoDao;
    public String[] getLastPay(int days){
          String titles="";
          String values="";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat titelDf = new SimpleDateFormat("MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE,-days);
          for (int i=0;i<days;i++){
              cal.add(Calendar.DATE,1);
              titles+="'"+titelDf.format(cal.getTime())+"',";
              Double value=payInfoDao.getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(0,df.format(cal.getTime()),df.format(cal.getTime()));
              if(null==value){
                  value =0d;
              }
              values+=value+",";

          }
          String[] str=new String[]{titles,values};
          return  str;
    }

    public String[] getLastIncome(int days){
        String titles="";
        String values="";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat titelDf = new SimpleDateFormat("MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE,-days);
        for (int i=0;i<days;i++){
            cal.add(Calendar.DATE,1);
            titles+="'"+titelDf.format(cal.getTime())+"',";
            Double value=incomeInfoDao.getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(0,df.format(cal.getTime()),df.format(cal.getTime()));
            if(null==value){
                value =0d;
            }
            values+=value+",";

        }
        String[] str=new String[]{titles,values};
        return  str;
    }

    public Double getTodayPay(){
        return Double.valueOf(getLastPay(1)[1].replace(",",""));
    }
    public Double getTodayIncome(){
        return Double.valueOf(getLastIncome(1)[1].replace(",",""));
    }
    public Double getMonthPay(){
        Double value=  payInfoDao.getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(0,getMonthFirstDay(),getMonthLastDay());
        if(null==value){
            value=0d;
        }
        return value;
    }

    public Double getMonthIncome(){
        Double value=  incomeInfoDao.getSumByStatusAndOperateTimeAfterAndOperateTimerBefore(0,getMonthFirstDay(),getMonthLastDay());
        if(null==value){
            value=0d;
        }
        return value;
    }

    /**
     * 得到本月的第一天
     * @return
     */
    private  String getMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));

       return   df.format(calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    private  String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }
}

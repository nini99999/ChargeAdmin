package com.poshist.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String  datetoStr(Date date, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        return format.format(date);
    }
    public static void  toExcel(String[] titles,List<Object[]> dates,OutputStream out) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
        Sheet sheet = wb.createSheet(" sheet1");
        Row row = sheet.createRow(0);
        for(int i=0;i<titles.length;i++){
            row.createCell(i).setCellValue(titles[i]);
        }
        for(int i=0;i<dates.size();i++){
            row = sheet.createRow(i+1);
            for(int ii=0;ii<dates.get(i).length;ii++){
              row.createCell(ii).setCellValue(dates.get(i)[ii].toString());
            }
        }
        wb.write(out);
    }

}

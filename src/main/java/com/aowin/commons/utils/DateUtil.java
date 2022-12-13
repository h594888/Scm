package com.aowin.commons.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 9:39
 * @Description TODO
 * @Version 1.0
 */
public class DateUtil {
    public static String formatDateAndTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    //生成年月日
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return  dateStr;
    }

    //生成年月日时分秒
    public static String getDateTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(date);
        return  dateTime;
    }
}

package com.aowin.controller;

/**
 * @Author Jiaozehua
 * @Date 2022/12/2 23:47
 * @Description TODO
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        String date = "2022-12";
        String[] dateStrs=date.split("[^0-9]");//根据不是数字的字符拆分字符串
        String yearStr=dateStrs[0];
        String monthStr=dateStrs[1];//取出最后一组数字
        int i = Integer.parseInt(monthStr);
        String end = "";
        String endDate ="";
        if (i==12){
            i =0;
            end = String.valueOf( i+1);
            int iyear = Integer.parseInt(yearStr);
            String year = String.valueOf(iyear + 1);
            endDate = year+"-"+"0"+end;
        }else {
            end = String.valueOf( i+ 1);
            endDate =yearStr+"-"+end;
        }
        System.out.println("startDate = " + date);
        System.out.println("endDate = " + endDate);
//        StringBuffer sb =new StringBuffer();
//        sb.append(yearStr);
//        sb.append("-");
//        sb.append(startDate);
        //拼接字符串
//        returntestStr.subSequence(0,testStr.length()-n)+added;
    }
}

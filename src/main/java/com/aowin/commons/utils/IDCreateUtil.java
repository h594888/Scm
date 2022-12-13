package com.aowin.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Jiaozehua
 * @Date 2022/11/28 11:17
 * @Description TODO
 * @Version 1.0
 */
public class IDCreateUtil {
    //生成采购单、销售单编号
    public static String getId(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = format.format(new Date());
        return id;
    }
}

package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 收付款登记表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:34
 * @Description TODO payrecord（收付款登记表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRecord {
    private int recordId;
    private String payTime;
    private BigDecimal payPrice;
    private String account,orderCode;
    private int payType;
    private String createTime;
    private int status;

    //收支报表
    private float inPrice,outPrice;
    private int inPriceCount,outPriceCount;
}

package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 销售单明细表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:23
 * @Description TODO soitem（销售单明细表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoItem {
    //销售单编号(主键，外键)
    private BigDecimal soId;
    //产品编号（主键，外键）
    private String productCode;
    private float unitPrice;
    private int num;
    private String unitName;
    private float itemPrice;
    private String productName;
    private int newNum;
    private float price;
    private String name;
}

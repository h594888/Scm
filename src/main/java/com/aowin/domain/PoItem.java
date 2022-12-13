package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 采购单明细表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:30
 * @Description TODO poitem（采购单明细表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoItem {
    private BigDecimal poId;
    private String productCode;
    private float unitPrice;
    private int num;
    private String unitName;
    private float itemPrice;
    private int newNum;
    private float price;
    private float productItemTotal;
//    private String name;


    private String  productName;

}

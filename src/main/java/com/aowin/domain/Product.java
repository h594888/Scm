package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:13
 * @Description TODO product（产品表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    //产品编号(主键)
    private String productCode,
            name,unitName,createDate,remark;
    //产品类别序列号（外键）
    private int categoryId,
            num,poNum,soNum;
    private float price;

    private String categoryName;
    //修改前的产品编号
    private String oldProductCode;
    private String productName;
}

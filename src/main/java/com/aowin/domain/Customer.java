package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:25
 * @Description TODO customer（客户表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    //客户编号(主键)
    private String customerCode,
            name,password,contactor,
            address,postCode,tel,fax,createDate;
    private String oldCustomerCode;
    public Customer(String customerCode, String name) {
        this.customerCode = customerCode;
        this.name = name;
    }
}

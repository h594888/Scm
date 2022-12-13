package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 供应商表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:32
 * @Description TODO vender（供应商表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vender {
    //供应商编号(主键)
    private String venderCode,
            name,password,contactor,address,postCode,tel,fax,createDate;
    private String oldvenderCode;
    public Vender(String venderCode, String name) {
        this.venderCode = venderCode;
        this.name = name;
    }
}

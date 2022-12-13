package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 采购单主信息表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:26
 * @Description TODO pomain（采购单主信息表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoMain {
    //采购单编号(主键)
    private BigDecimal poId;
    //供应商编号（外键）,用户账号(外键)
    private String venderCode,account,
            createTime,remark,stockTime,stockUser,payTime,payUser,prePayTime,prePayUser,endTime,endUser;
    private float tipFee,productTotal,poTotal,prePayFee,unPay;
    private int payType,status;

    private String venderName,scmuserName;

    private List<PoItem> poItems;

    private int poMainCount ,endCount;
    private float poTotalPrice,payPrice;
}

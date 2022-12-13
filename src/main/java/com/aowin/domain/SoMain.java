package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 销售单主信息表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:19
 * @Description TODO somain（销售单主信息表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoMain {
    private BigDecimal soId;
    private String customerCode;
    private String account;    //用户账号
    private String createTime; //创建时间
    private Float tipFee;//附加费用
    private Float productTotal;    //产品总价
    private Float soTotal;// 总价格
    private int payType;  // 付款方式
    private Float prePayFee; //最低预付款金额
    private int status;  //处理状态
    private String remark;  //备注
    private String payTime;
    private String payUser;
    private String customerName,scmuserName;

    private List<SoItem> soItems;

    private float unPay;
    private int soMainCount ,endCount;
    private float soTotalPrice,payPrice;
}

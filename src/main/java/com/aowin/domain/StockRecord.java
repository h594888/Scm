package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存记录表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:16
 * @Description TODO stockrecord（库存记录表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRecord {
    private int stockId,
            stockNum,stockType;
    private String productCode,
            orderCode,stockTime,createUser;
    private float poTotal;
    private String productName;
    private int stockIdOutCount,stockNumOutCount;
    private float stockPrcieOutToTal;

    private int stockIdInCount,stockNumInCount;
    private float stockPrcieInToTal;

    private int productCodeCount;
    private int productNum;
    private int realNum;
}

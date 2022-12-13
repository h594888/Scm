package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 盘点表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:18
 * @Description TODO checkstock（盘点表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckStock {
    private int stockId,
            originNum,realNum;
    private String productCode,
            stockTime,createUser,description,type;
    private int numChange;
}

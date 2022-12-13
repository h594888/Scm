package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Jiaozehua
 * @Date 2022/12/2 12:15
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayCondition {
    private  Integer currentPage, pageSize,inOrOut,payType;
    private String poId,startTime, endTime,orderCode;
}

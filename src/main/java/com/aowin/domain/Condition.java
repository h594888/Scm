package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Jiaozehua
 * @Date 2022/11/29 20:19
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private  Integer currentPage, pageSize;
    private String poId,startTime, endTime,venderCode;
    private int payType, status;

    private String soId;
    private String customerCode;
}

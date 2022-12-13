package com.aowin.service;

import com.aowin.domain.*;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 21:58
 * @Description TODO
 * @Version 1.0
 */
public interface PayRecordService {
    Page<SoMain> querySoMainByPayRecord(Page<SoMain> page, Integer payType);

    int payRecordSomain(String soId, String name,Integer price);

    Page<PoMain> queryPoMainByPayRecord(Page<PoMain> page, Integer payType);

    int outPayRecord(String poId, String name, Integer payPrice);

    Page<PayRecord> queryPayCondition(Page<PayRecord> page, PayCondition payCondition);

    int queryPayPice(String soId);

    List<PayRecord> querySoMainPayReport(Page<PayRecord> page, String date, String endDate);

    int querySoMainPayReportCount(Page<PayRecord> page, String date, String endDate);

    List<PayRecord> queryPoMainPayReport(Page<PayRecord> page, String date, String endDate);

    int queryPoMainPayReportCount(Page<PayRecord> page, String date, String endDate);

    List<PayRecord> queryPayRecordAllCount();

}

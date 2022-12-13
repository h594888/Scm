package com.aowin.service;

import com.aowin.domain.Condition;
import com.aowin.domain.Page;
import com.aowin.domain.PoItem;
import com.aowin.domain.PoMain;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/28 9:22
 * @Description TODO
 * @Version 1.0
 */
public interface PoMainService {
    Page<PoMain> queryPoMainByPage(Page<PoMain> page);

    List<PoItem> getPoItemsByPoId(String poId);

    int addPoMain(PoMain poMain);

    int delPoMain(String poId);

    Page<PoMain> queryPoMainByPayType(Page<PoMain> page,int payType);

    PoMain getPoMainByPoID(String poID);
    List<PoItem> getItemsByPoID(String poID);

    int endPomain(String poId,String account);

    PoMain queryPoMainByPoID(String poId);

    int updatePomain(PoMain poMain);

//    Page<PoMain> queryPoMainCondition(Page<PoMain> page, String poId, String startTime,String endTime, String venderCode, int payType, int status);
    Page<PoMain> queryPoMainCondition(Page<PoMain> page, Condition condition);

    List<PoMain> queryPoReport(Page<PoMain> page, String startDate, String endDate);

    int queryPoReportCount(Page<PoMain> page, String startDate, String endDate);

    List<PoMain> queryPoAllCount();
}

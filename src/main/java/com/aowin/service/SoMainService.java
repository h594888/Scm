package com.aowin.service;

import com.aowin.domain.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/30 10:13
 * @Description TODO
 * @Version 1.0
 */
public interface SoMainService {
    Page<SoMain> querySoMainByPage(Page<SoMain> page);

    List<SoItem> getSoItemsBySoId(String soId);

    SoMain querySoMainBySoID(String soId);

    int updateSoMain(SoMain soMain);

    int addSoMain(SoMain soMain);

    @Transactional(propagation = Propagation.REQUIRED )
    int delSoMain(String soId);

    SoMain getSoMainBySoID(String soId);

    Page<SoMain> querySoMainByPayType(Page<SoMain> page, Integer payType);

    int endSomain(String soId, String account);

    Page<SoMain> querySoMainCondition(Page<SoMain> page, Condition condition);

    List<SoMain> querySoReport(Page<SoMain> page, String date, String endDate);

    int querySoReportCount(Page<SoMain> page, String date, String endDate);

    List<SoMain> querySoAllCount();

}

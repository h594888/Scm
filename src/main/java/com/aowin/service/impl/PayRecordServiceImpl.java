package com.aowin.service.impl;

import com.aowin.domain.*;
import com.aowin.mapper.PayRecordMapper;
import com.aowin.mapper.SoMainMapper;
import com.aowin.service.PayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 21:58
 * @Description TODO
 * @Version 1.0
 */
@Service
public class PayRecordServiceImpl implements PayRecordService {
    @Autowired
    private PayRecordMapper payRecordMapper;
    @Autowired
    private SoMainMapper soMainMapper;
    @Override
    public Page<SoMain> querySoMainByPayRecord(Page<SoMain> page, Integer payType) {
        page.setLists(payRecordMapper.querySoMainByPayRecord(page,payType));
        page.setTotalRecord(payRecordMapper.querySoMainByPayRecordCount(page,payType));
        return page;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int payRecordSomain(String soId, String name,Integer price) {
        int i = 0;
        SoMain soMain = soMainMapper.querySoMainBySoID(soId);
        int payType = soMain.getPayType();
        if (payType==3){
            payRecordMapper.updateSomainPrepayRecord(soId,name);
            i = payRecordMapper.addPrePayRecord(price,soId,name);
        }else {
            payRecordMapper.updateSomainpayRecord(soId, name);
            i =  payRecordMapper.addPayRecord(price,soId,name);
        }

        return i;
    }

    @Override
    public Page<PoMain> queryPoMainByPayRecord(Page<PoMain> page, Integer payType) {
        page.setLists(payRecordMapper.queryPoMainByPayRecord(page,payType));
        page.setTotalRecord(payRecordMapper.queryPoMainByPayRecordCount(page,payType));
        return page;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int outPayRecord(String poId, String name, Integer payPrice) {
        int i = 0;
        PoMain poMain = soMainMapper.queryPoMainByPoId(poId);
        int payType = poMain.getPayType();
        if (payType==3){
            payRecordMapper.updatePomainPrepayRecord(poId,name);
            i = payRecordMapper.addPomainPrePayRecord(payPrice,poId,name);
        }else {
            payRecordMapper.updatePomainpayRecord(poId, name);
            i =  payRecordMapper.addPomainPayRecord(payPrice,poId,name);
        }
        return i;
    }

    @Override
    public Page<PayRecord> queryPayCondition(Page<PayRecord> page, PayCondition payCondition) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(payRecordMapper.queryPayCondition(page, payCondition));
        page.setTotalRecord(payRecordMapper.queryPayConditionCount(page, payCondition));
        return page;
    }

    @Override
    public int queryPayPice(String soId) {
        return payRecordMapper.queryPayPrice(soId);
    }

    @Override
    public List<PayRecord> querySoMainPayReport(Page<PayRecord> page, String date, String endDate) {
        return payRecordMapper.querySoMainPayReport(page,date,endDate);
    }

    @Override
    public int querySoMainPayReportCount(Page<PayRecord> page, String date, String endDate) {
        return payRecordMapper.querySoMainPayReportCount(page,date,endDate);
    }

    @Override
    public List<PayRecord> queryPoMainPayReport(Page<PayRecord> page, String date, String endDate) {
        return payRecordMapper.queryPoMainPayReport(page,date,endDate);
    }

    @Override
    public int queryPoMainPayReportCount(Page<PayRecord> page, String date, String endDate) {
        return payRecordMapper.queryPoMainPayReportCount(page,date,endDate);
    }

    @Override
    public List<PayRecord> queryPayRecordAllCount() {
        PayRecord payRecord = new PayRecord();
        payRecord.setOutPrice(payRecordMapper.getOutPrice());
        payRecord.setOutPriceCount(payRecordMapper.getOutPriceCount());
        payRecord.setInPrice(payRecordMapper.getInPrice());
        payRecord.setInPriceCount(payRecordMapper.getInPriceCount());
        List<PayRecord> payRecordList = new ArrayList<>();
        payRecordList.add(payRecord);
        System.out.println(payRecord);
        return payRecordList;
    }
}

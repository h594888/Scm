package com.aowin.service.impl;

import com.aowin.domain.Condition;
import com.aowin.domain.Page;
import com.aowin.domain.PoItem;
import com.aowin.domain.PoMain;
import com.aowin.mapper.PoMainMapper;
import com.aowin.service.PoMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/28 9:23
 * @Description TODO
 * @Version 1.0
 */
@Service
public class PoMainServiceImpl implements PoMainService {
    @Autowired
    private PoMainMapper poMainMapper;
    @Override
    public Page<PoMain> queryPoMainByPage(Page<PoMain> page) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(poMainMapper.queryPoMainByPage(page));
        page.setTotalRecord(poMainMapper.getPoMainCount(page));
        return page;
    }

    @Override
    public List<PoItem> getPoItemsByPoId(String poId) {
        return poMainMapper.getPoItemsByPoId(poId);
    }

    //新增采购单
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addPoMain(PoMain poMain) {
        int j = 0;
        poMain.setPoTotal(poMain.getProductTotal()+poMain.getTipFee());
        j = poMainMapper.addPoMain(poMain);
        PoItem item = new PoItem();
        //添加采购明细,修改产品表采购在途数
        for (int i = 0; i < poMain.getPoItems().size(); i++) {
            System.out.println(poMain.getPoItems().size());
            item = poMain.getPoItems().get(i);
            item.setPoId(poMain.getPoId());
            item.setProductCode(item.getProductCode());
            item.setUnitPrice(item.getPrice());
            item.setNum(item.getNewNum());
            item.setItemPrice(item.getNum()*item.getUnitPrice());
            System.out.println(item);
            poMainMapper.addPoItem(item);
            poMainMapper.updateProduct(item.getProductCode(),item.getNum());
        }
        return j;
    }

    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public int delPoMain(String poId) {
        int num = 0;
        String productCode = "";
        List<PoItem> list = poMainMapper.queryPoMainProductCount(poId);
        for (int i = 0; i < list.size(); i++) {
            PoItem poItem = list.get(i);
             num = poItem.getNum();
            productCode = poItem.getProductCode();
            poMainMapper.updateDelProductPoNum(productCode,num);
        }
        poMainMapper.delPoItem(poId);
//        poMainMapper.delPoMain(poId);
        return poMainMapper.delPoMain(poId);
    }

    @Override
    public Page<PoMain> queryPoMainByPayType(Page<PoMain> page,int payType) {
        System.out.println(payType);
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(poMainMapper.queryPoMainByPayType(page, payType));
        page.setTotalRecord(poMainMapper.getPoMainCountByPayType(page,payType));
        return page;
    }

    @Override
    public PoMain getPoMainByPoID(String poID) {
        return poMainMapper.getPoMainByPoID(poID);
    }

    @Override
    public List<PoItem> getItemsByPoID(String poID) {
        return poMainMapper.getItemsByPoID(poID);
    }

    //了结
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int endPomain(String poId,String account) {
        return poMainMapper.endPomain(poId,account);
    }

    @Override
    public PoMain queryPoMainByPoID(String poId) {
        return poMainMapper.queryPoMainByPoID(poId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updatePomain(PoMain poMain) {
//        poMainMapper.delPoItem(String.valueOf(poMain.getPoId()));
        System.out.println(String.valueOf(poMain.getPoId()));
        int i = 0;
         i = this.delPoMain(String.valueOf(poMain.getPoId()));
         i = this.addPoMain(poMain);
        return i;
    }
    //条件查询
//    @Override
//    public Page<PoMain> queryPoMainCondition(Page<PoMain> page, String poId, String startTime,String endTime, String venderCode, int payType, int status) {
//        System.out.println(payType);
//        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
//        page.setLists(poMainMapper.queryPoMainCondition(page, poId,startTime,endTime,venderCode,payType,status));
//        page.setTotalRecord(poMainMapper.queryPoMainConditionCount(page, poId,startTime,endTime,venderCode,payType,status));
//        return page;
//    }
    @Override
    public Page<PoMain> queryPoMainCondition(Page<PoMain> page, Condition condition) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(poMainMapper.queryPoMainCondition(page, condition));
        page.setTotalRecord(poMainMapper.queryPoMainConditionCount(page, condition));
        return page;
    }

    @Override
    public List<PoMain> queryPoReport(Page<PoMain> page, String startDate, String endDate) {
        return poMainMapper.queryPoReport(page,startDate,endDate);
    }

    @Override
    public int queryPoReportCount(Page<PoMain> page,String startDate, String endDate) {
        return poMainMapper.queryPoReportCount(page,startDate,endDate);
    }

    @Override
    public List<PoMain> queryPoAllCount() {
        PoMain poMain = new PoMain();
        poMain.setPoMainCount(poMainMapper.getPoCount());
        poMain.setEndCount(poMainMapper.getEndCount());
        poMain.setPayPrice(poMainMapper.getPayPrice());
        poMain.setPoTotalPrice(poMainMapper.getPoTotalPrice());
        List<PoMain> poMainList = new ArrayList<>();
        poMainList.add(poMain);
        System.out.println(poMain);
        return poMainList;
    }
}

package com.aowin.service.impl;

import com.aowin.domain.*;
import com.aowin.mapper.SoMainMapper;
import com.aowin.service.SoMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/30 10:13
 * @Description TODO
 * @Version 1.0
 */
@Service
public class SoMainServiceImpl implements SoMainService {
    @Autowired
    private SoMainMapper soMainMapper;

    @Override
    public Page<SoMain> querySoMainByPage(Page<SoMain> page) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(soMainMapper.querySoMainByPage(page));
        page.setTotalRecord(soMainMapper.getSoMainCount(page));
        return page;
    }

    @Override
    public List<SoItem> getSoItemsBySoId(String soId) {
        return soMainMapper.getSoItemsBySoId(soId);
    }

    @Override
    public SoMain querySoMainBySoID(String soId) {
        return soMainMapper.querySoMainBySoID(soId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addSoMain(SoMain soMain) {
        int j = 0;
        soMain.setSoTotal(soMain.getProductTotal()+soMain.getTipFee());
        j =  soMainMapper.addSoMain(soMain);
        SoItem item = new SoItem();
        //添加采购明细,修改产品表采购在途数
        for (int i = 0; i < soMain.getSoItems().size(); i++) {
            System.out.println(soMain.getSoItems().size());
            item = soMain.getSoItems().get(i);
            item.setSoId(soMain.getSoId());
            item.setProductCode(item.getProductCode());
            item.setUnitPrice(item.getPrice());
            item.setNum(item.getNewNum());
            item.setItemPrice(item.getNum()*item.getPrice());
            System.out.println(item);
            soMainMapper.addSoItem(item);
            soMainMapper.updateProduct(item.getProductCode(),item.getNum());
        }
        return j;
    }

    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public int delSoMain(String soId) {
        int num = 0;
        String productCode = "";
        List<PoItem> list = soMainMapper.querySoMainProductCount(soId);
        System.out.println(list.get(0).getNum());
        for (int i = 0; i < list.size(); i++) {
            PoItem poItem = list.get(i);
            num = poItem.getNum();
            productCode = poItem.getProductCode();
            soMainMapper.updateDelProductSoNum(productCode,num);
        }
        soMainMapper.delSoItem(soId);
        return soMainMapper.delSoMain(soId);
    }

    //修改采购单，若是不想改变采购单状态，只需要得到当前采购单的状态，添加中设置即可
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateSoMain(SoMain soMain) {
        System.out.println(String.valueOf(soMain.getSoId()));
        int i = 0;
        i = this.delSoMain(String.valueOf(soMain.getSoId()));
        i = this.addSoMain(soMain);
        return i;
    }
    @Override
    public SoMain getSoMainBySoID(String soId) {
        return soMainMapper.querySoMainBySoID(soId);
    }

    //销售单了结查询
    @Override
    public Page<SoMain> querySoMainByPayType(Page<SoMain> page, Integer payType) {
            System.out.println(payType);
            page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
            page.setLists(soMainMapper.querySoMainByPayType(page, payType));
            page.setTotalRecord(soMainMapper.getSoMainCountByPayType(page,payType));
            return page;
    }
    //销售单了结
    @Override
    public int endSomain(String soId, String account) {
            return soMainMapper.endSomain(soId,account);
    }

    @Override
    public Page<SoMain> querySoMainCondition(Page<SoMain> page, Condition condition) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setLists(soMainMapper.querySoMainCondition(page, condition));
        page.setTotalRecord(soMainMapper.querySoMainConditionCount(page, condition));
        return page;
    }

    //销售单月报
    @Override
    public List<SoMain> querySoReport(Page<SoMain> page, String startDate, String endDate) {
        return soMainMapper.querySoReport(page,startDate,endDate);
    }
    @Override
    public int querySoReportCount(Page<SoMain> page,String startDate, String endDate) {
        return soMainMapper.querySoReportCount(page,startDate,endDate);
    }

    @Override
    public List<SoMain> querySoAllCount() {
        SoMain soMain = new SoMain();
        soMain.setSoMainCount(soMainMapper.getSoCount());
        soMain.setEndCount(soMainMapper.getEndCount());
        soMain.setPayPrice(soMainMapper.getPayPrice());
        soMain.setSoTotalPrice(soMainMapper.getSoTotalPrice());
        List<SoMain> soMainList = new ArrayList<>();
        soMainList.add(soMain);
        System.out.println(soMain);
        return soMainList;
    }

}

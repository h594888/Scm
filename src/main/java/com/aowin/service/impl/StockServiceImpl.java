package com.aowin.service.impl;

import com.aowin.domain.*;
import com.aowin.mapper.PoMainMapper;
import com.aowin.mapper.ProductMapper;
import com.aowin.mapper.SoMainMapper;
import com.aowin.mapper.StockMapper;
import com.aowin.service.PoMainService;
import com.aowin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 9:40
 * @Description TODO
 * @Version 1.0
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private PoMainMapper poMainMapper;
    @Autowired
    private SoMainMapper soMainMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Page<PoMain> queryPoMainByInStock(Page<PoMain> page, Integer payType) {
        page.setLists(stockMapper.queryPoMainByInStock(page,payType));
        page.setTotalRecord(stockMapper.queryPoMainByInStockCount(page,payType));
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int inStockPomain(String poId, String name) {
        int i = 0;
        stockMapper.updatePomainInStock(poId, name);
        List<PoItem> itemsByPoID = poMainMapper.getItemsByPoID(poId);
        for (PoItem poItem : itemsByPoID) {
            StockRecord stockRecord = new StockRecord();
            stockRecord.setProductCode(poItem.getProductCode());
            stockRecord.setOrderCode(poId);
            stockRecord.setStockNum(poItem.getNum());
            stockRecord.setCreateUser(name);
            stockMapper.addStockCord(stockRecord);
           i = stockMapper.updateProductPoNum(poItem.getProductCode(),poItem.getNum());
        }
        return i;
    }

    //查询出库的数据
    @Override
    public Page<SoMain> querySoMainByOutStock(Page<SoMain> page, Integer payType) {
        page.setLists(stockMapper.querySoMainByOutStock(page,payType));
        page.setTotalRecord(stockMapper.querySoMainByOutStockCount(page,payType));
        return page;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int outStockSomain(String soId, String name) {
        int i = 0;
        stockMapper.updateSomainOnStock(soId, name);
        List<SoItem> itemsBySoId = soMainMapper.getSoItemsBySoId(soId);
        for (SoItem soItem : itemsBySoId) {
            StockRecord stockRecord = new StockRecord();
            stockRecord.setProductCode(soItem.getProductCode());
            stockRecord.setOrderCode(soId);
            stockRecord.setStockNum(soItem.getNum());
            stockRecord.setCreateUser(name);
            stockMapper.addSoMainStockCord(stockRecord);
            i = stockMapper.updateProductSoNum(soItem.getProductCode(),soItem.getNum());
        }
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int checkStock(CheckStock checkStock,String name) {
        int i = 0;
       int originNum =  stockMapper.getProductNum(checkStock.getProductCode());
       StockRecord stockRecord = new StockRecord();
        if (checkStock.getType().equals("损耗")){
            stockMapper.updateProductNumByKui(checkStock.getNumChange(),checkStock.getProductCode());
            checkStock.setOriginNum(originNum);
            checkStock.setRealNum(originNum-checkStock.getNumChange());
            stockMapper.addcheckStockCord(checkStock,name);
            stockRecord.setProductCode(checkStock.getProductCode());
            stockRecord.setStockNum(checkStock.getNumChange());
            stockRecord.setStockType(4);
            stockRecord.setCreateUser(name);
           i =  stockMapper.addStockRecord(stockRecord);
        } else if (checkStock.getType().equals("盈余")) {
            stockMapper.updateProductNumByzhuan(checkStock.getNumChange(),checkStock.getProductCode());
            checkStock.setOriginNum(originNum);
            checkStock.setRealNum(originNum+checkStock.getNumChange());
            stockMapper.addcheckStockCord(checkStock,name);
            stockRecord.setProductCode(checkStock.getProductCode());
            stockRecord.setStockNum(checkStock.getNumChange());
            stockRecord.setStockType(3);
            stockRecord.setCreateUser(name);
          i =   stockMapper.addStockRecord(stockRecord);
        }
        return i;
    }

    @Override
    public List<Product> queryStockCondition(Page<Product> page, String productCode, String productName, int minNum, int maxNum) {
        return stockMapper.queryStockCondition( page,  productCode,  productName,  minNum, maxNum);
    }

    @Override
    public int queryStockConditionCount(Page<Product> page, String productCode, String productName, int minNum, int maxNum) {
        return stockMapper.queryStockConditionCount( page,  productCode,  productName,  minNum, maxNum);
    }

    //出库产品月度报表
    @Override
    public List<StockRecord> queryOutStockReport(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryOutStockReport( page,date, endDate);
    }

    @Override
    public int queryOutStockReportCount(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryOutStockReportCount(page,date, endDate);
    }

    @Override
    public List<StockRecord> queryOutStockAllCount() {
        StockRecord stockRecord = new StockRecord();
        stockRecord.setStockIdOutCount(stockMapper.getStockIdOutCount());
        stockRecord.setStockNumOutCount(stockMapper.getStockNumOutCount());
        stockRecord.setStockPrcieOutToTal(stockMapper.getStockPrcieOutToTal());
        List<StockRecord> stockRecordList = new ArrayList<>();
        stockRecordList.add(stockRecord);
        System.out.println(stockRecord);
        return stockRecordList;
    }

    //入库产品月度报表
    @Override
    public List<StockRecord> queryInStockReport(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryInStockReport( page,date, endDate);
    }

    @Override
    public int queryInStockReportCount(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryInStockReportCount(page,date, endDate);
    }

    @Override
    public List<StockRecord> queryInStockAllCount() {
        StockRecord stockRecord = new StockRecord();
        stockRecord.setStockIdInCount(stockMapper.getStockIdInCount());
        stockRecord.setStockNumInCount(stockMapper.getStockNumInCount());
        stockRecord.setStockPrcieInToTal(stockMapper.getStockPriceInToTal());
        List<StockRecord> stockRecordList = new ArrayList<>();
        stockRecordList.add(stockRecord);
        System.out.println(stockRecord);
        return stockRecordList;
    }

    //库存月度报表
    @Override
    public List<StockRecord> queryStockChangeReport(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryStockChangeReport( page,date, endDate);
    }

    @Override
    public int queryStockChangeReportCount(Page<StockRecord> page, String date, String endDate) {
        return stockMapper.queryStockChangeReportCount(page,date, endDate);
    }

    @Override
    public List<StockRecord> queryStockChangeAllCount() {
        StockRecord stockRecord = new StockRecord();
        stockRecord.setProductCodeCount(stockMapper.getProductCodeCount());
        stockRecord.setProductNum(stockMapper.getProductNumCount());
        List<StockRecord> stockRecordList = new ArrayList<>();
        stockRecordList.add(stockRecord);
        System.out.println(stockRecord);
        return stockRecordList;
    }


}

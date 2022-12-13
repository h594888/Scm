package com.aowin.service;

import com.aowin.domain.*;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 9:38
 * @Description TODO
 * @Version 1.0
 */
public interface StockService {
    Page<PoMain> queryPoMainByInStock(Page<PoMain> page, Integer payType);

    int inStockPomain(String poId, String name);

    Page<SoMain> querySoMainByOutStock(Page<SoMain> page, Integer payType);

    int outStockSomain(String soId, String name);

    int checkStock(CheckStock checkStock,String name);

    List<Product> queryStockCondition(Page<Product> page, String productCode, String productName, int minNum, int maxNum);

    int queryStockConditionCount(Page<Product> page, String productCode, String productName, int minNum, int maxNum);

    List<StockRecord> queryOutStockReport(Page<StockRecord> page, String date, String endDate);

    int queryOutStockReportCount(Page<StockRecord> page, String date, String endDate);

    List<StockRecord> queryOutStockAllCount();

    List<StockRecord> queryInStockReport(Page<StockRecord> page, String date, String endDate);

    int queryInStockReportCount(Page<StockRecord> page, String date, String endDate);

    List<StockRecord> queryInStockAllCount();

    List<StockRecord> queryStockChangeReport(Page<StockRecord> page, String date, String endDate);

    int queryStockChangeReportCount(Page<StockRecord> page, String date, String endDate);

    List<StockRecord> queryStockChangeAllCount();

}

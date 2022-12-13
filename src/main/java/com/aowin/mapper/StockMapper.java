package com.aowin.mapper;

import com.aowin.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 9:23
 * @Description TODO
 * @Version 1.0
 */
public interface StockMapper {
    List<PoMain> queryPoMainByInStock(@Param("page") Page<PoMain> page,@Param("payType") int payType);
    int queryPoMainByInStockCount(@Param("page") Page<PoMain> page,@Param("payType") int payType);

    int updatePomainInStock(@Param("poId") String poId, @Param("name") String name);

    int addStockCord(@Param("stockRecord") StockRecord stockRecord);

    int updateProductPoNum(@Param("productCode") String productCode,@Param("num") int num);

    List<SoMain> querySoMainByOutStock(@Param("page") Page<SoMain> page,@Param("payType") Integer payType);

    int querySoMainByOutStockCount(@Param("page")Page<SoMain> page, @Param("payType")Integer payType);

    int updateSomainOnStock(@Param("soId") String soId, @Param("name") String name);

    int addSoMainStockCord(@Param("stockRecord")StockRecord stockRecord);

    int updateProductSoNum(@Param("productCode")String productCode, @Param("num")int num);


    int updateProductNumByKui(@Param("numChange") int numChange, @Param("productCode") String productCode);

    int updateProductNumByzhuan(@Param("numChange")int numChange,@Param("productCode") String productCode);

    int addcheckStockCord(@Param("checkStock") CheckStock checkStock,@Param("name") String name);

    int getProductNum(@Param("productCode") String productCode);

    int addStockRecord(@Param("stockRecord") StockRecord stockRecord);

    List<Product> queryStockCondition(@Param("page") Page<Product> page,@Param("productCode") String productCode,@Param("productName") String productName,@Param("minNum") int minNum,@Param("maxNum") int maxNum);

    int queryStockConditionCount(@Param("page") Page<Product> page,@Param("productCode") String productCode,@Param("productName") String productName,@Param("minNum") int minNum,@Param("maxNum") int maxNum);

    //出库报表
    List<StockRecord> queryOutStockReport(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int queryOutStockReportCount(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    @Select("select count(StockID)stockIdOutCount from stockrecord where StockType = 2 or StockType = 4")
    int getStockIdOutCount();
    @Select("select sum(StockNum)stockNumOutCount from stockrecord where StockType = 2 or StockType = 4")
    int getStockNumOutCount();
    @Select("select sum((sr.StockNum*p.Price))stockPrcieOutToTal from stockrecord sr\n" +
            "inner join product p on sr.ProductCode = p.ProductCode " +
            "where StockType = 2 or StockType = 4")
    float getStockPrcieOutToTal();

    //入库月度报表
    List<StockRecord> queryInStockReport(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int queryInStockReportCount(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    @Select("select count(StockID)stockIdOutCount from stockrecord where StockType = 1 or StockType = 3 ")
    int getStockIdInCount();
    @Select("select sum(StockNum)stockNumOutCount from stockrecord where StockType = 1 or StockType = 3")
    int getStockNumInCount();
    @Select("select sum((sr.StockNum*p.Price))stockPrcieOutToTal from stockrecord sr\n" +
            "inner join product p on sr.ProductCode = p.ProductCode " +
            "where StockType = 1 or StockType = 3")
    float getStockPriceInToTal();

    List<StockRecord> queryStockChangeReport(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int queryStockChangeReportCount(@Param("page")Page<StockRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    @Select("select count(ProductCode) from product")
    int getProductCodeCount();
    @Select("select sum(num) from product")
    int getProductNumCount();
}

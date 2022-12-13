package com.aowin.mapper;

import com.aowin.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/30 10:13
 * @Description TODO
 * @Version 1.0
 */
public interface SoMainMapper {
    List<SoMain> querySoMainByPage(@Param("page") Page<SoMain> page);

    int getSoMainCount(@Param("page") Page<SoMain> page);

    List<SoItem> getSoItemsBySoId(String soId);

    SoMain querySoMainBySoID(@Param("soid") String soId);

    int addSoMain(@Param("somain") SoMain soMain);

    //添加采购明细
    int addSoItem(@Param("soitem") SoItem soItem);
    //修改产品里采购在途数
    int updateProduct(@Param("productcode") String productCode, @Param("num") int num);
    int updateDelProductSoNum(@Param("productcode") String productCode, @Param("num") int num);

    List<PoItem> querySoMainProductCount(String soId);

    void delSoItem(String soId);

    int delSoMain(String soId);

    List<SoMain> querySoMainByPayType(@Param("page") Page<SoMain> page,@Param("payType") Integer payType);

    int getSoMainCountByPayType(@Param("page")Page<SoMain> page,@Param("payType")  Integer payType);

    int endSomain(@Param("soId") String soId, @Param("account") String account);

    List<SoMain> querySoMainCondition(@Param("page") Page<SoMain> page,@Param("condition") Condition condition);

    int querySoMainConditionCount(@Param("page") Page<SoMain> page,@Param("condition") Condition condition);

    PoMain queryPoMainByPoId(@Param("poid")String poId);

    //销售月度报表
    List<SoMain> querySoReport(@Param("page")Page<SoMain> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int querySoReportCount(@Param("page")Page<SoMain> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    @Select("select count(*) from somain")
    int getSoCount();
    @Select("select count(*) from somain where Status = 4")
    int getEndCount();

    @Select("select sum(SOTotal) from somain")
    float getSoTotalPrice();
    @Select("select sum(payPrice) from payrecord where payType= 1 or  payType = 3;")
    float getPayPrice();

}

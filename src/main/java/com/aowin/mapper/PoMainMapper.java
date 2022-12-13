package com.aowin.mapper;

import com.aowin.domain.Condition;
import com.aowin.domain.Page;
import com.aowin.domain.PoItem;
import com.aowin.domain.PoMain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/28 9:24
 * @Description TODO
 * @Version 1.0
 */
public interface PoMainMapper {
    List<PoMain> queryPoMainByPage(@Param("page") Page<PoMain> page);
    int getPoMainCount(@Param("page")Page<PoMain> page);

    List<PoItem> getPoItemsByPoId(String poId);

    //添加采购主信息表单
    int addPoMain(@Param("pomain") PoMain poMain);
    //添加采购明细
    int addPoItem(@Param("poitem") PoItem poItem);
    //修改产品里采购在途数
    int updateProduct(@Param("productcode") String productCode, @Param("num") int num);
    int updateDelProductPoNum(@Param("productcode") String productCode, @Param("num") int num);

    List<PoItem> queryPoMainProductCount(String poId);

    void delPoItem(String poId);

    int delPoMain(String poId);

    List<PoMain> queryPoMainByPayType(@Param("page") Page<PoMain> page,@Param("payType") int payType);
    int getPoMainCountByPayType(@Param("page") Page<PoMain> page,@Param("payType") int payType);

    PoMain getPoMainByPoID(@Param("poid") String poID);
    List<PoItem> getItemsByPoID(@Param("poid") String poID);

    int getPomainPayType(String poId);

    int queryPomainStatus(String poId);

    int endPomain(@Param("poId") String poId,@Param("account") String account);


    PoMain queryPoMainByPoID(String poId);

    List<PoMain> queryPoMainCondition(@Param("page") Page<PoMain> page,@Param("condition") Condition condition);

    int queryPoMainConditionCount(@Param("page") Page<PoMain> page, @Param("condition") Condition condition);



    //采购月度报表
    List<PoMain> queryPoReport(@Param("page")Page<PoMain> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int queryPoReportCount(@Param("page")Page<PoMain> page, @Param("startDate") String startDate, @Param("endDate")String endDate);
    @Select("select count(*) from pomain")
    int getPoCount();
    @Select("select count(*) from pomain where Status = 4")
    int getEndCount();
    @Select("select sum(POTotal) from pomain")
    float getPoTotalPrice();
    @Select("select sum(payPrice) from payrecord where payType= 2 or  payType = 4;")
    float getPayPrice();
}


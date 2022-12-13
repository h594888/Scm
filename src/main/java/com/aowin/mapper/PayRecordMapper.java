package com.aowin.mapper;

import com.aowin.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 22:02
 * @Description TODO
 * @Version 1.0
 */
public interface PayRecordMapper {
    List<SoMain> querySoMainByPayRecord(@Param("page") Page<SoMain> page,@Param("payType") Integer payType);

    int querySoMainByPayRecordCount(@Param("page")Page<SoMain> page,@Param("payType") Integer payType);

    int updateSomainpayRecord(@Param("soId") String soId,@Param("name") String name);
    int updateSomainPrepayRecord(@Param("soId") String soId,@Param("name") String name);

    int addPayRecord(@Param("price") int price, @Param("soId")String soId,@Param("name") String name);

    int addPrePayRecord(@Param("price")int price, @Param("soId") String soId,@Param("name") String name);

    List<PoMain> queryPoMainByPayRecord(@Param("page") Page<PoMain> page,@Param("payType") Integer payType);

    int queryPoMainByPayRecordCount(@Param("page")Page<PoMain> page,@Param("payType") Integer payType);

    int updatePomainPrepayRecord(@Param("poId")String poId,@Param("name") String name);

    int addPomainPrePayRecord(@Param("price")Integer price, @Param("poId") String poId,@Param("name") String name);

    int updatePomainpayRecord(@Param("poId")String poId,@Param("name") String name);

    int addPomainPayRecord(@Param("price")Integer price, @Param("poId") String poId,@Param("name") String name);

    List<PayRecord> queryPayCondition(@Param("page") Page<PayRecord> page,@Param("payCondition") PayCondition payCondition);

    int queryPayConditionCount(@Param("page") Page<PayRecord> page,@Param("payCondition") PayCondition payCondition);

    @Select("select payPrice from payRecord where orderCode=#{id}")
    int queryPayPrice(@Param("id") String id);

    List<PayRecord> querySoMainPayReport(@Param("page")Page<PayRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int querySoMainPayReportCount(@Param("page")Page<PayRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    List<PayRecord> queryPoMainPayReport(@Param("page")Page<PayRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    int queryPoMainPayReportCount(@Param("page")Page<PayRecord> page, @Param("startDate") String  startDate, @Param("endDate")String endDate);

    @Select("select sum(payPrice) from payrecord where payType = 2 or payType = 4")
    float getOutPrice();
    @Select("select count(recordID)  from payrecord where payType = 2 or payType = 4")
    int getOutPriceCount();
    @Select("select sum(payPrice) from payrecord where payType = 1 or payType = 3")
    float getInPrice();
    @Select("select count(recordID)  from payrecord where payType = 1 or payType = 3")
    int getInPriceCount();
}

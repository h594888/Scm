package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.*;
import com.aowin.service.PayRecordService;
import com.aowin.service.PoMainService;
import com.aowin.service.SoMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 21:57
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class PayRecordController {
    @Autowired
    private PayRecordService payRecordService;
    @Autowired
    private PoMainService poMainService;

    @Autowired
    private SoMainService soMainService;

    @RequestMapping("querySoMainByPayRecord")
    public Result querySoMainByPayRecord(Integer currentPage,Integer pageSize,Integer payType){
        Page<SoMain> page = new Page<>();
        page.setCurrentPage((currentPage-1)*pageSize);
        page.setPageSize(pageSize);
        Page<SoMain> soMainPage = payRecordService.querySoMainByPayRecord(page, payType);
        return new Result(Constant.SUCCESS,"ok",soMainPage);
    }
    @RequestMapping("inPayRecord")
    public Result inPayRecord(String soId,Integer payPrice,HttpServletRequest request){
        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");
        String name = scmUser.getName();
        int i = payRecordService.payRecordSomain(soId, name,payPrice);
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }

    @RequestMapping("queryPoMainByPayRecord")
    public Result queryPoMainByPayRecord(Integer currentPage,Integer pageSize,Integer payType){
        Page<PoMain> page = new Page<>();
        page.setCurrentPage((currentPage-1)*pageSize);
        page.setPageSize(pageSize);
        Page<PoMain> poMainPage = payRecordService.queryPoMainByPayRecord(page, payType);
        return new Result(Constant.SUCCESS,"ok",poMainPage);
    }


    @RequestMapping("outPayRecord")
    public Result outPayRecord(String poId,Integer payPrice,HttpServletRequest request){
        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");
        String name = scmUser.getName();
        int i = payRecordService.outPayRecord(poId, name,payPrice);
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }


    //销售条件查询
    @PostMapping("queryPayCondition")
    public Result queryPayCondition(@RequestBody PayCondition payCondition){
        Page<PayRecord> page = new Page<>(payCondition.getCurrentPage(),payCondition.getPageSize(),0,null);
        Page<PayRecord> payRecordPage = payRecordService.queryPayCondition(page, payCondition);
        System.out.println(payRecordPage);
        return new Result(Constant.SUCCESS,"ok",payRecordPage);
    }

    //收支月度报表查询
    @GetMapping("queryPoMainPayReport")
    public Result queryPoMainPayReport(Integer currentPage,Integer pageSize,String date){
        String endDate ="";
        if (!date.equals("")&& !date.equals("null")){
            String[] dateStrs=date.split("[^0-9]");//根据不是数字的字符拆分字符串
            String yearStr=dateStrs[0];
            String monthStr=dateStrs[1];//取出最后一组数字
            int i = Integer.parseInt(monthStr);
            String end = "";
            if (i==12){
                i =0;
                end = String.valueOf( i+1);
                int iyear = Integer.parseInt(yearStr);
                String year = String.valueOf(iyear + 1);
                endDate = year+"-"+"0"+end;
            }else if (i<9){
                end = String.valueOf( i+ 1);
                endDate =yearStr+"-"+"0"+end;
            }else {
                end = String.valueOf( i+ 1);
                endDate =yearStr+"-"+end;
            }
        }
        Page<PayRecord> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<PayRecord> payRecordList = payRecordService.queryPoMainPayReport(page,date,endDate);
        page.setLists(payRecordList);
        page.setTotalRecord(payRecordService.queryPoMainPayReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }

    //收支月度报表查询
    @GetMapping("querySoMainPayReport")
    public Result querySoMainPayReport(Integer currentPage,Integer pageSize,String date){
        String endDate ="";
        if (!date.equals("")&& !date.equals("null")){
            String[] dateStrs=date.split("[^0-9]");//根据不是数字的字符拆分字符串
            String yearStr=dateStrs[0];
            String monthStr=dateStrs[1];//取出最后一组数字
            int i = Integer.parseInt(monthStr);
            String end = "";
            if (i==12){
                i =0;
                end = String.valueOf( i+1);
                int iyear = Integer.parseInt(yearStr);
                String year = String.valueOf(iyear + 1);
                endDate = year+"-"+"0"+end;
            }else if (i<9){
                end = String.valueOf( i+ 1);
                endDate =yearStr+"-"+"0"+end;
            }else {
                end = String.valueOf( i+ 1);
                endDate =yearStr+"-"+end;
            }
        }
        Page<PayRecord> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<PayRecord> payRecordList = payRecordService.querySoMainPayReport(page,date,endDate);
        page.setLists(payRecordList);
        page.setTotalRecord(payRecordService.querySoMainPayReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }



    @RequestMapping("queryPayRecordAllCount")
    public Result queryPayRecordAllCount(){
        List<PayRecord> payRecordList =  payRecordService.queryPayRecordAllCount();
        return new Result(Constant.SUCCESS,"ok",payRecordList);
    }
}

package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.*;
import com.aowin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/12/1 9:40
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("queryPoMainByInStock")
    public Result queryPoMainByInStock(Integer currentPage,Integer pageSize,Integer payType){
        Page<PoMain> page = new Page<>();
        page.setCurrentPage((currentPage-1)*pageSize);
        page.setPageSize(pageSize);
        Page<PoMain> poMainPage = stockService.queryPoMainByInStock(page, payType);
        return new Result(Constant.SUCCESS,"ok",poMainPage);
    }

    @RequestMapping("inStockPomain")
    public Result inStockPomain(String poId, HttpServletRequest request){
        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");
        String name = scmUser.getName();
        int i = stockService.inStockPomain(poId, name);
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }
    //销售单
    @RequestMapping("querySoMainByOutStock")
    public Result querySoMainByOutStock(Integer currentPage,Integer pageSize,Integer payType){
        Page<SoMain> page = new Page<>();
        page.setCurrentPage((currentPage-1)*pageSize);
        page.setPageSize(pageSize);
        Page<SoMain> soMainPage = stockService.querySoMainByOutStock(page, payType);
        return new Result(Constant.SUCCESS,"ok",soMainPage);
    }
    @RequestMapping("outStockSomain")
    public Result outStockSomain(String soId, HttpServletRequest request){
        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");
        String name = scmUser.getName();
        int i = stockService.outStockSomain(soId, name);
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }

    //盘点查询
    @RequestMapping("checkStock")
    public Result checkStock(@RequestBody CheckStock checkStock,HttpServletRequest request){
        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");
        String name = scmUser.getName();
       int i = stockService.checkStock(checkStock,name);
       if (i>0){
           return new Result(Constant.SUCCESS,"ok",null);
       }else {
           return new Result(Constant.FAIL,"fail",null);
       }
    }

    //库存查询

    @RequestMapping("queryStockCondition")
    public Result queryStockCondition(Integer currentPage,Integer pageSize,String productCode,String productName,Integer minNum,Integer maxNum){
        Page<Product> page = new Page<>();
        page.setCurrentPage((currentPage-1)*pageSize);
        page.setPageSize(pageSize);
        List<Product> productList = stockService.queryStockCondition(page, productCode, productName, minNum, maxNum);
        for (Product product : productList) {
            product.setProductCode(product.getProductCode());
            product.setProductName(product.getName());
        }
        page.setLists(productList);
        page.setTotalRecord(stockService.queryStockConditionCount(page,productCode, productName, minNum, maxNum));
        System.out.println(page.getLists()+"---------------------------------");
        return new Result(null,null,page);
    }

    //出库产品月度报表

    @RequestMapping("queryOutStockReport")
    public Result queryOutStockReport(Integer currentPage,Integer pageSize,String date){
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
        Page<StockRecord> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<StockRecord> stockRecordList = stockService.queryOutStockReport(page, date,endDate);
        page.setLists(stockRecordList);
        page.setTotalRecord(stockService.queryOutStockReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }



    @RequestMapping("queryOutStockAllCount")
    public Result queryOutStockAllCount(){
        List<StockRecord> stockRecordList =  stockService.queryOutStockAllCount();
        return new Result(Constant.SUCCESS,"ok",stockRecordList);
    }

    //入库产品月度报表

    @RequestMapping("queryInStockReport")
    public Result queryInStockReport(Integer currentPage,Integer pageSize,String date){
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
        Page<StockRecord> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<StockRecord> stockRecordList = stockService.queryInStockReport(page, date,endDate);
        page.setLists(stockRecordList);
        page.setTotalRecord(stockService.queryInStockReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }

    @RequestMapping("queryInStockAllCount")
    public Result queryInStockAllCount(){
        List<StockRecord> stockRecordList =  stockService.queryInStockAllCount();
        return new Result(Constant.SUCCESS,"ok",stockRecordList);
    }

    //库存月度报表

    @RequestMapping("queryStockChangeReport")
    public Result queryStockChangeReport(Integer currentPage,Integer pageSize,String date){
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
        Page<StockRecord> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<StockRecord> stockRecordList = stockService.queryStockChangeReport(page, date,endDate);
        page.setLists(stockRecordList);
        page.setTotalRecord(stockService.queryStockChangeReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }

    @RequestMapping("queryStockChangeAllCount")
    public Result queryStockChangeAllCount(){
        List<StockRecord> stockRecordList =  stockService.queryStockChangeAllCount();
        return new Result(Constant.SUCCESS,"ok",stockRecordList);
    }


}

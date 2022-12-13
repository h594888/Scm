package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.commons.utils.DateUtil;
import com.aowin.commons.utils.IDCreateUtil;
import com.aowin.domain.*;
import com.aowin.service.CustomerService;
import com.aowin.service.SoMainService;
import com.aowin.service.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author Jiaozehua
 * @Date 2022/11/30 10:12
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class SoMainController {
    @Autowired
    private SoMainService soMainService;
    @Autowired
    private CustomerService customerService;
    //采购单分页
    @RequestMapping("querySoMainByPage")
    public Result querySoMainByPage(Integer currentPage, Integer pageSize){
        Page<SoMain> page = new Page<>(currentPage,pageSize,0,null);
        Page<SoMain> soMainPage = soMainService.querySoMainByPage(page);
        for (SoMain list : soMainPage.getLists()) {
            System.out.println(list+"------------------------------------------------------------------");
        }
        System.out.println(soMainPage);
        return new Result(Constant.SUCCESS,"ok",soMainPage);
    }

    //根据销售编号查询明细
    @RequestMapping("getSoItemsBySoId")
    public Result getSoItemsBySoId(String soId){
        List<SoItem> soItems = soMainService.getSoItemsBySoId(soId);
        System.out.println(soItems);
        return new Result(Constant.SUCCESS,"ok",soItems);
    }

    //查询基础查询
    @GetMapping("getSoMainBasicMassage")
    public Result getBasicMassage(HttpServletRequest request){
        SoMain so = new SoMain();
        ScmUser u=(ScmUser) request.getSession().getAttribute("loginUser");
        so.setAccount(u.getAccount());
        so.setCreateTime(DateUtil.getDateTime());
        so.setSoId(new BigDecimal(IDCreateUtil.getId()));
        return new Result(Constant.SUCCESS,"ok",so);
    }

    @GetMapping("getCustomerMassage")
    public Result getCustomerMassage(){
        List<Customer> customerList = customerService.getAll();
        System.out.println(customerList);
        return new Result(Constant.SUCCESS,"ok",customerList);

    }

    //添加销售单
    @RequestMapping("addOrUpdateSoMain")
    public Result addOrUpdateSoMain(@RequestBody SoMain soMain){
        int i = 0;
        System.out.println(soMain.getSoItems());
        System.out.println(soMain.getStatus());
        if (soMainService.querySoMainBySoID(String.valueOf(soMain.getSoId()))!=null){
            i = soMainService.updateSoMain(soMain);
        }else {
            i= soMainService.addSoMain(soMain);
        }
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }

    //删除销售单
    @RequestMapping("delSoMain")
    public Result delSoMain(String soId){
        soMainService.delSoMain(soId);
        return new Result(Constant.SUCCESS,"ok",null);
    }

    //修改销售单单的页面数据回填
    @GetMapping("getSoMainBySoId")
    public Result getSoMainBySoID(String soId){
        SoMain soMain = soMainService.getSoMainBySoID(soId);
        System.out.println(soMain);
        soMain.setScmuserName(soMain.getAccount());
        soMain.setProductTotal(soMain.getSoTotal());
        return new Result(Constant.SUCCESS,"ok",soMain);
    }

    @GetMapping("getSoItemBySoId/{soId}")
    public Result getSoItemBySoId(@PathVariable("soId") String soId){
        List<SoItem> soItemList = soMainService.getSoItemsBySoId(soId);
        System.out.println(soItemList);
        for (SoItem soItem : soItemList) {
            soItem.setPrice(soItem.getUnitPrice());
            soItem.setName(soItem.getProductName());
        }
        return new Result(Constant.SUCCESS,"ok",soItemList);
    }

    //销售单单了结
    @RequestMapping("querySoMainByPayType")
    public Result querySoMainByPayType(Integer currentPage,Integer pageSize,Integer payType){
        Page<SoMain> page = new Page<>(currentPage,pageSize,0,null);
        Page<SoMain> soMainPage = soMainService.querySoMainByPayType(page,payType);
        System.out.println(soMainPage);
        return new Result(Constant.SUCCESS,"ok",soMainPage);
    }

    @RequestMapping("endSomain")
    public Result endSomain(String soId,HttpServletRequest request){
        ScmUser u = (ScmUser) request.getSession().getAttribute("loginUser");
        String account = u.getName();
        int i = soMainService.endSomain(soId,account);
        if (i>0){
            return new Result(Constant.SUCCESS,"成功",null);
        }else {
            return new Result(Constant.FAIL,"失败",null);
        }
    }
    //销售条件查询
    @PostMapping("querySoMainCondition")
    public Result querySoMainCondition(@RequestBody Condition condition){
        Page<SoMain> page = new Page<>(condition.getCurrentPage(),condition.getPageSize(),0,null);
        Page<SoMain> soMainPage = soMainService.querySoMainCondition(page, condition);
        System.out.println(soMainPage);
        return new Result(Constant.SUCCESS,"ok",soMainPage);
    }

    //采购月度报表

    @RequestMapping("querySoReport")
    public Result querySoReport(Integer currentPage,Integer pageSize,String date){
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

        Page<SoMain> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<SoMain> poMainList = soMainService.querySoReport(page, date,endDate);
        for (SoMain soMain : poMainList) {
            soMain.setUnPay(soMain.getSoTotal()-soMain.getPrePayFee());
        }
        page.setLists(poMainList);
        page.setTotalRecord(soMainService.querySoReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }


    @RequestMapping("querySoAllCount")
    public Result querySoAllCount(){
        List<SoMain> soMainList =  soMainService.querySoAllCount();
        return new Result(Constant.SUCCESS,"ok",soMainList);
    }
}

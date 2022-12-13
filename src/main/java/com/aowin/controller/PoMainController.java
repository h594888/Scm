package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.commons.utils.DateUtil;
import com.aowin.commons.utils.IDCreateUtil;
import com.aowin.domain.*;
import com.aowin.service.PoMainService;
import com.aowin.service.VenderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/28 9:22
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class PoMainController {
    @Autowired
    private PoMainService poMainService;
    @Autowired
    private VenderService venderService;

    //采购单分页
    @RequestMapping("queryPoMainByPage")
    public Result queryPoMainByPage(Integer currentPage,Integer pageSize){
        Page<PoMain> page = new Page<>(currentPage,pageSize,0,null);
        Page<PoMain> poMainPage = poMainService.queryPoMainByPage(page);
        System.out.println(poMainPage);
        return new Result(Constant.SUCCESS,"ok",poMainPage);
    }
    //根据采购编号查询明细
    @RequestMapping("getPoItemsByPoId")
    public Result getPoItemsByPoId(String poId){
        List<PoItem> poItems = poMainService.getPoItemsByPoId(poId);
        System.out.println(poItems);
        return new Result(Constant.SUCCESS,"ok",poItems);
    }


    //查询基础查询
    @GetMapping("getBasicMassage")
    public Result getBasicMassage(HttpServletRequest request){
        PoMain po = new PoMain();
        ScmUser u=(ScmUser) request.getSession().getAttribute("loginUser");
        po.setAccount(u.getAccount());
        po.setCreateTime(DateUtil.getDateTime());
        po.setPoId(new BigDecimal(IDCreateUtil.getId()));
        return new Result(Constant.SUCCESS,"ok",po);
    }
    //查出供应商的信息
    @GetMapping("getVenderMassage")
    public Result getVenderMassage(){
        List<Vender> venderList = venderService.getAll();
        return new Result(Constant.SUCCESS,"ok",venderList);
    }
    //添加采购单
    @RequestMapping("addOrUpdatePoMain")
    public Result addOrUpdatePoMain(@RequestBody PoMain poMain){
        int i = 0;
        System.out.println(poMain.getPoItems());
        System.out.println(poMain.getStatus());
        if (poMainService.queryPoMainByPoID(String.valueOf(poMain.getPoId()))!=null){
           i = poMainService.updatePomain(poMain);
        }else {
            i= poMainService.addPoMain(poMain);
        }
        if (i>0){
            return new Result(Constant.SUCCESS,"ok",null);
        }else {
            return new Result(Constant.FAIL,"fail",null);
        }
    }

    //删除采购单
    @RequestMapping("delPoMain")
    public Result delPoMain(String poId){
        poMainService.delPoMain(poId);
        return new Result(Constant.SUCCESS,"ok",null);
    }

    //采购单了结
    @RequestMapping("queryPoMainByPayType")
    public Result queryPoMainByPayType(Integer currentPage,Integer pageSize,Integer payType){
        Page<PoMain> page = new Page<>(currentPage,pageSize,0,null);
        Page<PoMain> poMainPage = poMainService.queryPoMainByPayType(page,payType);
        System.out.println(poMainPage);
        return new Result(Constant.SUCCESS,"ok",poMainPage);
    }

    //修改采购单的页面数据回填
    @GetMapping("getPoMainByPoId")
    public Result getPoMainByPoID(String poId){
        PoMain poMain = poMainService.getPoMainByPoID(poId);
        System.out.println(poMain);
        poMain.setScmuserName(poMain.getAccount());
        poMain.setProductTotal(poMain.getPoTotal());
        return new Result(Constant.SUCCESS,"ok",poMain);
    }

    @GetMapping("getPoItemsByPoId/{poId}")
    public Result getPoItemsByPoID(@PathVariable("poId") String poId){
        List<PoItem> poItemList = poMainService.getItemsByPoID(poId);
        System.out.println(poItemList);
        for (PoItem poItem : poItemList) {
            poItem.setPrice(poItem.getUnitPrice());
            poItem.setProductName(poItem.getProductName());
        }
        return new Result(Constant.SUCCESS,"ok",poItemList);
    }

    @RequestMapping("endPomain")
    public Result endPomain(String poId,HttpServletRequest request){
        ScmUser u = (ScmUser) request.getSession().getAttribute("loginUser");
        String account = u.getName();
        int i = poMainService.endPomain(poId,account);
        if (i>0){
            return new Result(Constant.SUCCESS,"成功",null);
        }else {
            return new Result(Constant.FAIL,"失败",null);
        }
    }

    //采购单条件查询
    @PostMapping("queryPoMainCondition")
    public Result queryPoMainCondition(@RequestBody Condition condition){
        Page<PoMain> page = new Page<>(condition.getCurrentPage(),condition.getPageSize(),0,null);
        Page<PoMain> poMainPage = poMainService.queryPoMainCondition(page, condition);
        System.out.println(poMainPage);
        return new Result(Constant.SUCCESS,"ok",poMainPage);
    }

    //采购月度报表

    @RequestMapping("queryPoReport")
    public Result queryPoReport(Integer currentPage,Integer pageSize,String date){
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

        Page<PoMain> page = new Page<>((currentPage-1)*pageSize,pageSize,0,null);
        List<PoMain> poMainList = poMainService.queryPoReport(page, date,endDate);
        for (PoMain poMain : poMainList) {
            poMain.setUnPay(poMain.getPoTotal()-poMain.getPrePayFee());
        }
         page.setLists(poMainList);
         page.setTotalRecord(poMainService.queryPoReportCount(page, date,endDate));
        return new Result(Constant.SUCCESS,"ok",page);
    }

    @RequestMapping("queryPoAllCount")
    public Result queryPoAllCount(){
        List<PoMain> poMainList =  poMainService.queryPoAllCount();
        return new Result(Constant.SUCCESS,"ok",poMainList);
    }
}

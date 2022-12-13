package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import com.aowin.service.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 19:59
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class VenderController {
    @Autowired
    private VenderService venderService;

    //分页查询
    @RequestMapping("queryVenderByPage")
    public Result queryVenderByPage(Integer currentPage,Integer pageSize,String venderCode,String name){
        Page<Vender> venderPage = venderService.queryVenderByPage(new Page<Vender>(currentPage, pageSize, 0, null), venderCode, name);
        System.out.println(venderPage);
        return new Result(Constant.SUCCESS,"ok",venderPage);
    }

    //
    @RequestMapping("addVender")
    public Result addVender(@RequestBody Vender vender){
        int i = venderService.addVender(vender);
        if (i>0){
            return new Result(Constant.SUCCESS,"OK",null);
        }else {
            return new Result(Constant.FAIL,"No",null);
        }
    }

    @RequestMapping("delVender")
    public Result delVender(String venderCode){
        int i = venderService.delVender(venderCode);
        if (i>0){
            return new Result(Constant.SUCCESS,"OK",null);
        }else {
            return new Result(Constant.FAIL,"No",null);
        }
    }
}

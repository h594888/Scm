package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.Page;
import com.aowin.domain.ScmUser;
import com.aowin.service.ScmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 14:19
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class ScmUserController {
    @Autowired
    private ScmUserService scmUserService;

    @RequestMapping("getUsers")
    public Result getUsers( Integer currentPage, Integer pageSize, String account, String name){
        Page<ScmUser> page = new Page<>(currentPage,pageSize,0,null);
        ScmUser scmUser = new ScmUser();
        scmUser.setAccount(account);
        scmUser.setName(name);
        Page<ScmUser> scmUserPage =
                scmUserService.queryUserByPage(page,scmUser);
        System.out.println(scmUserPage);
        return new Result(null,null, scmUserPage);
    }

    @RequestMapping("delUser")
    public Result delUser(String account){
        int i = scmUserService.delUserByAccount(account);
        if (i>0){
            return new Result(Constant.SUCCESS,null, null);
        }else {
            return new Result(Constant.FAIL,null, null);
        }
    }

    @RequestMapping("addUser")
    public Result addUser(@RequestBody ScmUser scmUser){
        int i = scmUserService.addUser(scmUser);
        if (i>0){
            return new Result(Constant.SUCCESS,"操作成功！",null);
        }else {
            return new Result(Constant.FAIL,"操作失败！",null);
        }
    }
    @RequestMapping("UpdateUser")
    public Result UpdateUser(@RequestBody ScmUser scmUser){
        String oldAccount = scmUser.getOldAccount();
        int i = scmUserService.updateUser(scmUser);
        if (i>0){
            return new Result(Constant.SUCCESS,"操作成功！",null);
        }else {
            return new Result(Constant.FAIL,"操作失败！",null);
        }
    }
}

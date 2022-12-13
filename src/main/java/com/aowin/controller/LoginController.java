package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.ScmUser;
import com.aowin.service.ScmUserService;
import com.aowin.service.impl.ScmUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 11:16
 * @Description TODO
 * @Version 1.0
 */
@Controller
public class LoginController {
    @Autowired
    private ScmUserService scmUserService;

    @RequestMapping("login")
    @ResponseBody
    public Result login(@RequestBody ScmUser scmUser, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        map.put("account",scmUser.getAccount());
        map.put("password",scmUser.getPassword());
        ScmUser loginUser = scmUserService.queryUserByActAndPwd(map);
        if (loginUser == null){
            return new Result(Constant.FAIL,"账号或密码错误！",null);
        }else if (loginUser.getStatus() == 1){
            return new Result(Constant.FAIL,"账号被锁定，请联系管理员处理！",null);
        }else {
            session.setAttribute("loginUser",loginUser);
            return new Result(Constant.SUCCESS,"登录成功！",null);
        }
    }

    @RequestMapping("getAccount")
    @ResponseBody
    public Result getAccount(HttpSession session){
        ScmUser loginUser = (ScmUser) session.getAttribute("loginUser");
        return new Result(null,null,loginUser.getAccount());
    }
}

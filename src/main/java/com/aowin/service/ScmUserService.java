package com.aowin.service;

import com.aowin.domain.Page;
import com.aowin.domain.ScmUser;

import java.util.Map;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 11:12
 * @Description TODO
 * @Version 1.0
 */
public interface ScmUserService {
    ScmUser queryUserByActAndPwd(Map<String,Object> map);

    Page<ScmUser> queryUserByPage(Page<ScmUser> page,ScmUser scmUser);

    int delUserByAccount(String account);

    int addUser(ScmUser scmUser);
    int updateUser(ScmUser scmUser);
}

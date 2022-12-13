package com.aowin.service.impl;

import com.aowin.domain.Page;
import com.aowin.domain.ScmUser;
import com.aowin.mapper.ScmUserMapper;
import com.aowin.service.ScmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 11:13
 * @Description TODO
 * @Version 1.0
 */
@Service
public class ScmUserServiceImpl implements ScmUserService {
    @Autowired
    private ScmUserMapper scmUserMapper;
    @Override
    public ScmUser queryUserByActAndPwd(Map<String, Object> map) {
        return scmUserMapper.queryUserByActAndPwd(map);
    }

    @Override
    public Page<ScmUser> queryUserByPage(Page<ScmUser> page, ScmUser scmUser) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setTotalRecord(scmUserMapper.getScmUsersCount(scmUser));
        List<ScmUser> scmUserList = scmUserMapper.queryUsersByPage(page, scmUser);
        page.setLists(scmUserList);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delUserByAccount(String account) {
        int i = 0;
        if (scmUserMapper.delUserModelByAccount(account)>0 && scmUserMapper.delUserByAccount(account)>0){
            i = 1;
        }
        return i ;
    }

    //事务注解
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addUser(ScmUser scmUser) {
        ScmUser user = scmUserMapper.checkScmUser(scmUser.getAccount());
        int i = 0;
        if (user!=null){
            delUserByAccount(user.getAccount());
        }
        i = scmUserMapper.insertScmUser(scmUser);
        if (scmUser.getModelName().size() == 4){
            i = scmUserMapper.insertModelName(scmUser.getAccount(),"admin");
        }else {
            for (int j = 0; j < scmUser.getModelName().size(); j++) {
                i = scmUserMapper.insertModelName(scmUser.getAccount(),scmUser.getModelName().get(j));
            }
        }
        return i;
    }

    //事务注解
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUser(ScmUser scmUser) {
        int j = 0;
        String oldAccount = scmUser.getOldAccount();
        delUserByAccount(oldAccount);
        scmUserMapper.insertScmUser(scmUser);
        if (scmUser.getModelName().size() == 4){
//            scmUserMapper.updateModelCode(1001,"admin",oldAccount);
            j =scmUserMapper.insertModelName(scmUser.getAccount(),"admin");
        }else {
            for (int i = 0; i < scmUser.getModelName().size(); i++) {
                j = scmUserMapper.insertModelName(scmUser.getAccount(),scmUser.getModelName().get(i));
            }
        }
//        System.err.println(scmUserMapper.updateScmUser(scmUser));
//        int i = scmUserMapper.updateScmUser(scmUser);
        return j;
    }
}

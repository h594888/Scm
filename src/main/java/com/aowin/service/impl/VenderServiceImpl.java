package com.aowin.service.impl;

import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import com.aowin.mapper.VenderMapper;
import com.aowin.service.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 19:54
 * @Description TODO
 * @Version 1.0
 */
@Service
public class VenderServiceImpl implements VenderService {
    @Autowired
    private VenderMapper venderMapper;

    @Override
    public List<Vender> getAll() {
        return venderMapper.getAllVender();
    }

    @Override
    public Page<Vender> queryVenderByPage(Page<Vender> page,String venderCode,String name) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setTotalRecord(venderMapper.getVenderCount(page,new Vender(venderCode,name)));
        page.setLists(venderMapper.queryVenderByPage(page,new Vender(venderCode,name)));
        return page;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addVender(Vender vender) {
        if (vender.getOldvenderCode()!=null&&!(vender.getOldvenderCode().equals(""))){
            return venderMapper.updateVender(vender);
        }else {
            return venderMapper.addVender(vender);
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delVender(String venderCode) {
        return venderMapper.delVender(venderCode);
    }

}

package com.aowin.service;

import com.aowin.domain.Page;
import com.aowin.domain.Vender;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 19:53
 * @Description TODO
 * @Version 1.0
 */
public interface VenderService {
     List<Vender> getAll();

    Page<Vender> queryVenderByPage(Page<Vender> page, String venderCode, String name);

    int addVender(Vender vender);

    int delVender(String venderCode);

}

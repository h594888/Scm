package com.aowin.mapper;

import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 19:40
 * @Description TODO
 * @Version 1.0
 */
public interface VenderMapper {
    //分页查询供应商
    List<Vender> queryVenderByPage(@Param("page") Page<Vender> page, @Param("vender") Vender vender);
    int getVenderCount(@Param("page") Page<Vender> page,@Param("vender") Vender vender);

    int addVender(Vender vender);

    int updateVender(Vender vender);

    int delVender(String venderCode);

    List<Vender> getAllVender();
}

package com.aowin.mapper;

import com.aowin.domain.Customer;
import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/29 22:54
 * @Description TODO
 * @Version 1.0
 */
public interface CustomerMapper {
    List<Customer> getAllCustomer();

    List<Customer> queryCustomerByPage(@Param("page") Page<Customer> page, @Param("customer") Customer customer);
    int getCustomerCount(@Param("page") Page<Customer> page,@Param("customer") Customer customer);

    int updateCustomer(Customer customer);

    int addCustomer(Customer customer);

    int delCustomer(String customerCode);
}

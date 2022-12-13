package com.aowin.service;

import com.aowin.domain.Customer;
import com.aowin.domain.Page;
import com.aowin.domain.Vender;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/29 22:48
 * @Description TODO
 * @Version 1.0
 */
public interface CustomerService {
    List<Customer> getAll();

    Page<Customer> queryCustomerByPage(Page<Customer> customerPage, String customerCode, String name);

    int addCustomer(Customer customer);

    int delCustomer(String customerCode);
}

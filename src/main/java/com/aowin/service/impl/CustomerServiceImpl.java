package com.aowin.service.impl;

import com.aowin.domain.Customer;
import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import com.aowin.mapper.CustomerMapper;
import com.aowin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/29 22:49
 * @Description TODO
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> getAll() {
        return customerMapper.getAllCustomer();
    }
    @Override
    public Page<Customer> queryCustomerByPage(Page<Customer> page, String customerCode, String name) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setTotalRecord(customerMapper.getCustomerCount(page,new Customer(customerCode,name)));
        page.setLists(customerMapper.queryCustomerByPage(page,new Customer(customerCode,name)));
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addCustomer(Customer customer) {
        if (customer.getOldCustomerCode()!=null&&!(customer.getOldCustomerCode().equals(""))){
            return customerMapper.updateCustomer(customer);
        }else {
            return customerMapper.addCustomer(customer);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delCustomer(String customerCode) {
        return customerMapper.delCustomer(customerCode);
    }
}

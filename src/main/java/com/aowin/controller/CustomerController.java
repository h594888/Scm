package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.Customer;
import com.aowin.domain.Page;
import com.aowin.domain.Vender;
import com.aowin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jiaozehua
 * @Date 2022/11/29 22:48
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //分页查询
    @RequestMapping("queryCustomerByPage")
    public Result queryCustomerByPage(Integer currentPage, Integer pageSize, String customerCode , String name){
        Page<Customer> customerPage = customerService.queryCustomerByPage(new Page<Customer>(currentPage, pageSize, 0, null), customerCode, name);
        System.out.println(customerPage);
        return new Result(Constant.SUCCESS,"ok",customerPage);
    }

    //
    @RequestMapping("addCustomer")
    public Result addCustomer(@RequestBody Customer customer){
        int i = customerService.addCustomer(customer);
        if (i>0){
            return new Result(Constant.SUCCESS,"OK",null);
        }else {
            return new Result(Constant.FAIL,"No",null);
        }
    }

    @RequestMapping("delCustomer")
    public Result delCustomer(String customerCode){
        int i = customerService.delCustomer(customerCode);
        if (i>0){
            return new Result(Constant.SUCCESS,"OK",null);
        }else {
            return new Result(Constant.FAIL,"No",null);
        }
    }
}

package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.domain.Product;
import com.aowin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
*@Author Jiaozehua
*@Date 2022/11/25 11:44
*@Description TODO
*@Version 1.0
*/
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("getProducts")
    public Result getProducts(Integer currentPage,Integer pageSize){
        Page<Product> productPage = productService.queryProductByPage(new Page<>(currentPage, pageSize, 0, null));
        for (Product list : productPage.getLists()) {
            list.setProductName(list.getName());
        }
        System.err.println(productPage);
        return new Result(Constant.SUCCESS,"ok",productPage);
    }

    @RequestMapping("addOrUpdateProduct")
    public Result addOrUpdateProduct(@RequestBody Product product){
//        String createDate =product.getCreateDate();
//        System.out.println(createDate);
        System.out.println(product);
        System.out.println(product.getOldProductCode());
        int i = productService.addProduct(product);
        if (i>0){
            return new Result(Constant.SUCCESS,"操作成功",null);
        }else {
            return new Result(Constant.FAIL,"操作失败",null);
        }
    }

    @RequestMapping("delProduct")
    public Result delProduct(String productCode){
        System.out.println(productCode);
        productService.delProduct(productCode);
        return new Result(Constant.SUCCESS,"删除成功！",null);
    }

    //查询分类信息
    @RequestMapping("categoryNameMassage")
    public Result getCategoryNameMassage(){
        List<Category> categoryNameMassage = productService.getCategoryNameMassage();
        List<Object> list = new ArrayList<>();
        for (Category category : categoryNameMassage) {
            list.add(category.getName());
        }
        System.out.println(list);
        return new Result(Constant.SUCCESS,"ok！",list);
    }
}

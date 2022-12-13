package com.aowin.service;

import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.domain.Product;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 11:55
 * @Description TODO
 * @Version 1.0
 */
public interface ProductService {

    Page<Product> queryProductByPage(Page<Product> page);

    int addProduct(Product product);

    int delProduct(String productCode);

    List<Category> getCategoryNameMassage();
}

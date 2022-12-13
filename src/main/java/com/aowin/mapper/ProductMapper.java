package com.aowin.mapper;

import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 11:56
 * @Description TODO
 * @Version 1.0
 */
public interface ProductMapper {
    //分页查询产品
    int queryProductCount();
    List<Product> queryProductByPage(@Param("page") Page<Product> page);

    //添加产品
    int addProduct(Product product);

    int delProduct(String productCode);

    int updateProduct(Product product);

    List<Category> getCategoryNameMassage();
}

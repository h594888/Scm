package com.aowin.service.impl;

import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.domain.Product;
import com.aowin.mapper.ProductMapper;
import com.aowin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 11:55
 * @Description TODO
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<Product> queryProductByPage(Page<Product> page) {
        page.setCurrentPage((page.getCurrentPage() - 1) * page.getPageSize());
        page.setTotalRecord(productMapper.queryProductCount());
        page.setLists(productMapper.queryProductByPage(page));
        return page;
    }

    ;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addProduct(Product product) {
        int i = 0;
        if (product.getOldProductCode()!=null&&!(product.getOldProductCode().equals(""))){
            i = productMapper.updateProduct(product);
            System.err.println("修改" +
                    "-----------------------------------------------------------");
        }else {
           i = productMapper.addProduct(product);
        }
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delProduct(String productCode) {
        return productMapper.delProduct(productCode);
    }

    @Override
    public List<Category> getCategoryNameMassage() {
        return productMapper.getCategoryNameMassage();
    }
}

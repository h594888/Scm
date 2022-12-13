package com.aowin.service.impl;

import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.mapper.CategoryMapper;
import com.aowin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 9:53
 * @Description TODO
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<Category> queryCategoryByPage(Page<Category> page) {
        page.setCurrentPage((page.getCurrentPage()-1)* page.getPageSize());
        page.setTotalRecord(categoryMapper.getCategoryCount());
        page.setLists(categoryMapper.queryCategory(page));
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addOrUpdateCategory(Category category) {
        int i = 0;
        if (category.getCategoryId()!=0){
            i = categoryMapper.updateCategory(category);
        }else {
            i = categoryMapper.addCategory(category);
        }
       return i;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delCategory(Integer categoryId) {
        return categoryMapper.delCategory(categoryId);
    }
}

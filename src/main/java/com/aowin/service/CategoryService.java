package com.aowin.service;

import com.aowin.domain.Category;
import com.aowin.domain.Page;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 9:52
 * @Description TODO
 * @Version 1.0
 */
public interface CategoryService {
    Page<Category> queryCategoryByPage(Page<Category> page);

    int addOrUpdateCategory(Category category);

    int delCategory(Integer categoryId);
}

package com.aowin.controller;

import com.aowin.commons.Result.Result;
import com.aowin.commons.constants.Constant;
import com.aowin.domain.Category;
import com.aowin.domain.Page;
import com.aowin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 9:58
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/queryCategory")
    public Result queryCategory(Integer currentPage, Integer pageSize){
        Page<Category> categoryPage = categoryService.queryCategoryByPage(new Page<>(currentPage, pageSize, 0, null));
        return new Result(Constant.SUCCESS,"ok",categoryPage);
    }

    @RequestMapping("addOrUpdateCategory")
    public Result addOrUpdateCategory(@RequestBody Category category){
        categoryService.addOrUpdateCategory(category);
        return new Result(Constant.SUCCESS,"操作成功！",null);
    }

    @RequestMapping("delCategory")
    public Result delCategory(Integer categoryId){
        categoryService.delCategory(categoryId);
        return new Result(Constant.SUCCESS,"删除成功！",null);
    }
}

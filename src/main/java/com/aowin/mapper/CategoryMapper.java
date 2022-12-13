package com.aowin.mapper;

import com.aowin.domain.Category;
import com.aowin.domain.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/25 9:48
 * @Description TODO
 * @Version 1.0
 */
public interface CategoryMapper {
    @Select("select * from category limit #{currentPage},#{pageSize}")
    List<Category> queryCategory(Page<Category> page);

    @Select("select count(*) from category")
    int getCategoryCount();

    @Insert("insert into category (name,remark) values (#{name},#{remark})")
    int  addCategory( Category category);

    @Delete("delete from category where categoryId = #{categoryId}")
    int delCategory(Integer categoryId);

    @Update("update category set name = #{name},remark=#{remark} where categoryId = #{categoryId}")
    int updateCategory(Category category);
}

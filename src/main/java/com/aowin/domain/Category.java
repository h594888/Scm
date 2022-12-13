package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品分类表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:11
 * @Description TODO category（产品分类表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int categoryId;
    private String name,remark;
}

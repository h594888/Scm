package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户模块表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:10
 * @Description TODO usermodel（用户模块表）
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    //用户账号(主键)
    private String account;
    //模块编号（主键）
    private int modelCode;
}

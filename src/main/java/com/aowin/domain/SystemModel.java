package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模块表
 * @Author Jiaozehua
 * @Date 2022/11/23 15:09
 * @Description TODO systemmodel（模块表）
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemModel {
    //模块编码(主键)
    private int modelCode;
    private String
            //模块名称
            modelName,
            //模块路径
            modelUri;
}

package com.aowin.commons.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 9:43
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code; //状态码
    private String message; //信息
    private Object data; //可拓展其他类
}

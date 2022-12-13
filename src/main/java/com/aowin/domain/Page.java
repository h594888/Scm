package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Jiaozehua
 * @Date 2022/11/24 13:16
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private int currentPage;
    private int pageSize;
    private int totalRecord;
    private List<T> lists;
}

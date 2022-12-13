package com.aowin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScmUser {
    private String account;

    private String password;
    private String name;

    private String createDate;

    private Integer status;
    //model名字
    private ArrayList<String> modelName;

    //旧的account
    private String oldAccount;


}
package com.wini.restapitemplate.user.model;

import lombok.Data;

@Data
public class User {

    private int user_sn;
    private String user_id;
    private String user_pwd;
    private String user_tel;
    private String user_nm;
    private String use_yn;
    private String reg_date;
    private String upd_date;
}

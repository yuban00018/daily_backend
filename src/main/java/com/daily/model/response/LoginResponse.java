package com.daily.model.response;

import lombok.Data;

import java.util.Date;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Data
public class LoginResponse {
    private Integer id;
    private String email;
    private long level;
    private long exp;
    private String type;
    private Date signUpDate;
    private String token;
    private String name;
    private String avatar;
}

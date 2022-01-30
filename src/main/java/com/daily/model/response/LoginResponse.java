package com.daily.model.response;

import lombok.Data;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Data
public class LoginResponse {
    private String token;
    private String name;
    private String avatar;
}

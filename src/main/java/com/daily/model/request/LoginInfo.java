package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Data
public class LoginInfo {

    @NotBlank(message="密码不能为空")
    private String password;

    @NotBlank(message="用户名不能为空")
    private String name;
}

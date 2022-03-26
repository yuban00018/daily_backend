package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterInfo {
    @NotBlank(message="密码不能为空")
    private String password;

    @NotBlank(message="用户名不能为空")
    private String name;

    @Email(message = "邮箱错误")
    private String email;
}

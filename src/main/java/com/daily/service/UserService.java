package com.daily.service;

import com.daily.model.request.LoginInfo;
import com.daily.model.request.RegisterInfo;
import com.daily.model.response.Result;
import org.springframework.stereotype.Service;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
public interface UserService {
    /**
     * @Author: yuban00018
     * @Date: 2022/1/30
     * @Return: Result
     * @Description: 登录接口
     */
    Result login(LoginInfo loginInfo);
    Result getInfo();
    Result register(RegisterInfo registerInfo);
}

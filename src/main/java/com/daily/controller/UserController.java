package com.daily.controller;

import com.daily.model.request.LoginInfo;
import com.daily.model.response.Result;
import com.daily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mortbay.log.Log;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/info")
    public Result getInfo(){
        return userService.getInfo();
    }

    @PostMapping("/login")
    public Result Login(@Validated @RequestBody LoginInfo loginInfo){
        log.info("UserName: " + loginInfo.getName());
        log.info("UserPassword: " + loginInfo.getPassword());
        return userService.login(loginInfo);
    }
}

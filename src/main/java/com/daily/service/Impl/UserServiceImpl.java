package com.daily.service.Impl;

import com.daily.dao.daily.BlackListDoMapper;
import com.daily.dao.daily.PasswordDoMapper;
import com.daily.dao.daily.UserDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.daily.*;
import com.daily.model.request.LoginInfo;
import com.daily.model.request.RegisterInfo;
import com.daily.model.response.LoginResponse;
import com.daily.model.response.Result;
import com.daily.model.response.UserInfoResponse;
import com.daily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.daily.tools.JwtTool;
import com.daily.tools.ResultTool;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PasswordDoMapper passwordDoMapper;
    @Resource
    private UserDoMapper userDoMapper;
    @Resource
    private JwtTool jwtTool;
    @Resource
    private BlackListDoMapper blackListDoMapper;

    @Override
    public Result register(RegisterInfo registerInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andNameEqualTo(registerInfo.getName());
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        if(userDoList.isEmpty()){
            UserDo userDo = new UserDo();
            BeanUtils.copyProperties(registerInfo, userDo);
            userDo.setSignUpDate(new Date());
            userDo.setType("User");
            userDo.setLevel((long)0);
            userDo.setExp((long)0);
            userDoMapper.insert(userDo);
            userDoList = userDoMapper.selectByExample(userDoExample);
            if(userDoList.isEmpty()){
                return ResultTool.error(EmAllException.DATABASE_ERR);
            }else{
                userDo = userDoList.get(0);
                PasswordDo passwordDo = new PasswordDo();
                passwordDo.setPassword(registerInfo.getPassword());
                passwordDo.setUserId(userDo.getUserId());
                passwordDoMapper.insert(passwordDo);
                return ResultTool.success();
            }
        }else{
            return ResultTool.error(EmAllException.MULTI_USER);
        }
    }

    @Override
    public Result login(LoginInfo loginInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andNameEqualTo(loginInfo.getName());
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        if (userDoList.isEmpty()) {
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        } else {
            Integer id = userDoList.get(0).getUserId();
            PasswordDoExample passwordDoExample = new PasswordDoExample();
            passwordDoExample.createCriteria().andUserIdEqualTo(id).andPasswordEqualTo(loginInfo.getPassword());
            List<PasswordDo> passwordDoList = passwordDoMapper.selectByExample(passwordDoExample);
            if (passwordDoList.isEmpty()) {
                return ResultTool.error(EmAllException.NO_LOGIN_AUTHORIZATION);
            }
            BlackListDoExample blackListDoExample = new BlackListDoExample();
            blackListDoExample.createCriteria().andUserIdEqualTo(userDoList.get(0).getUserId());
            List<BlackListDo> blackListDos = blackListDoMapper.selectByExample(blackListDoExample);
            if (!blackListDos.isEmpty())
                return ResultTool.error(EmAllException.USER_IN_BLACK_LIST);
            LoginResponse loginResponse = new LoginResponse();
            UserDo userDo = userDoList.get(0);
            BeanUtils.copyProperties(userDo,loginResponse);
            //generate token
            loginResponse.setToken(jwtTool.createJwt(userDo.getUserId().toString(),userDo.getName()));
            loginResponse.setId(userDo.getUserId());
            return ResultTool.success(loginResponse);
        }
    }

    @Override
    public Result getInfo() {
        Integer id = Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDo, userInfoResponse);
        return ResultTool.success(userInfoResponse);
    }
}

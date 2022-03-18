package com.daily.service.Impl;

import com.daily.dao.PasswordDoMapper;
import com.daily.dao.UserDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.PasswordDo;
import com.daily.model.entity.PasswordDoExample;
import com.daily.model.entity.UserDo;
import com.daily.model.entity.UserDoExample;
import com.daily.model.request.LoginInfo;
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

    @Override
    public Result login(LoginInfo loginInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andNameEqualTo(loginInfo.getName());
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        if (userDoList.isEmpty()) {
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        } else {
            Integer id = userDoList.get(0).getId();
            PasswordDoExample passwordDoExample = new PasswordDoExample();
            passwordDoExample.createCriteria().andIdEqualTo(id).andPasswordEqualTo(loginInfo.getPassword());
            List<PasswordDo> passwordDoList = passwordDoMapper.selectByExample(passwordDoExample);
            if (passwordDoList.isEmpty()) {
                return ResultTool.error(EmAllException.NO_LOGIN_AUTHORIZATION);
            }
            LoginResponse loginResponse = new LoginResponse();
            UserDo userDo = userDoList.get(0);
            BeanUtils.copyProperties(userDo,loginResponse);
            //generate token
            loginResponse.setToken(jwtTool.createJwt(userDo.getId().toString(),userDo.getName()));
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

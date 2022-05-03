package com.daily;

import com.daily.dao.daily.PasswordDoMapper;
import com.daily.dao.daily.UserDoMapper;
import com.daily.model.entity.daily.PasswordDo;
import com.daily.model.entity.daily.PasswordDoExample;
import com.daily.model.entity.daily.UserDo;
import com.daily.model.entity.daily.UserDoExample;
import com.daily.model.response.LoginResponse;
import com.daily.model.response.UserInfoResponse;
import com.daily.tools.JwtTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
public class UserTest {
    @Resource
    private PasswordDoMapper passwordDoMapper;
    @Resource
    private UserDoMapper userDoMapper;
    @Resource
    private JwtTool jwtTool;

    @Test
    void registerTest() {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andNameEqualTo("unit test");
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        UserDo userDo = new UserDo();
        userDo.setName("unit test");
        userDo.setSignUpDate(new Date());
        userDo.setType("User");
        userDo.setLevel((long) 0);
        userDo.setExp((long) 0);
        userDoMapper.insertSelective(userDo);
        userDoList = userDoMapper.selectByExample(userDoExample);
        userDo = userDoList.get(0);
        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setPassword("test");
        passwordDo.setUserId(userDo.getUserId());
        passwordDoMapper.insert(passwordDo);
    }

    @Test
    void loginTest() {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andNameEqualTo("Admin");
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        Integer id = userDoList.get(0).getUserId();
        PasswordDoExample passwordDoExample = new PasswordDoExample();
        passwordDoExample.createCriteria().andUserIdEqualTo(id).andPasswordEqualTo("123456");
        List<PasswordDo> passwordDoList = passwordDoMapper.selectByExample(passwordDoExample);
        LoginResponse loginResponse = new LoginResponse();
        UserDo userDo = userDoList.get(0);
        BeanUtils.copyProperties(userDo, loginResponse);
        //generate token
        loginResponse.setToken(jwtTool.createJwt(userDo.getUserId().toString(), userDo.getName()));

    }

    @Test
    void infoTest() {
        Integer id = 1;
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDo, userInfoResponse);
    }
}

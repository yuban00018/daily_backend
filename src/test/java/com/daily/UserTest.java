package com.daily;

import com.daily.dao.daily.*;
import com.daily.model.entity.daily.*;
import com.daily.model.response.LoginResponse;
import com.daily.model.response.UserInfoResponse;
import com.daily.tools.JwtTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
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
    void infoTest(){
        Integer id = 1;
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDo, userInfoResponse);
        log.info(userInfoResponse.getUserId().toString());
    }
}

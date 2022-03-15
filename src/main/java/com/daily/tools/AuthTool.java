package com.daily.tools;


import com.daily.dao.UserDoMapper;
import com.daily.exception.AllException;
import com.daily.exception.EmAllException;
import com.daily.model.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


/*
 * @Author:Au_Miner
 * @Date:2022/3/12
 * @Description:登录接口
 */
@Slf4j
@Component
public class AuthTool {

    @Resource
    private UserDoMapper userDoMapper;

    /*
     * @Author:Au_Miner
     * @Date:2022/3/12
     * @Description:获取当前用户对象
     */
    public UserDo getUser() throws AllException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDo userDo = userDoMapper.selectByPrimaryKey(Integer.parseInt(userId));
        if (userDo == null) {
            throw new AllException(EmAllException.NO_SUCH_USER, "凭证对应的用户在系统中不存在！");
        }
        return userDo;
    }

    /*
     * @Author:Au_Miner
     * @Date:2022/3/12
     * @Description:获取当前用户id
     */
    public String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

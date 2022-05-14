package com.daily.security;

import com.daily.dao.daily.UserDoMapper;
import com.daily.model.entity.daily.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserDoMapper userDoMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //从数据库中获取这个用户
        UserDo userDo = userDoMapper.selectByPrimaryKey(Integer.parseInt(userId));
        if(userDo == null) {
            throw new UsernameNotFoundException("账号或密码错误!");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDo.getType()));

        return new JwtUser(userDo.getUserId().toString(), null, userDo.getType(), grantedAuthorities);
    }
}

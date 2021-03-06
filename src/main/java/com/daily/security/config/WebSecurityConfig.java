package com.daily.security.config;

import com.daily.security.JwtAccessDeniedHandler;
import com.daily.security.JwtTokenFilter;
import com.daily.security.JwtUnauthorizedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    JwtTokenFilter jwtTokenFilter;
    @Resource
    JwtUnauthorizedHandler jwtUnauthorizedHandler;
    @Resource
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login/**").permitAll()
                .antMatchers("/user/register/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/statistic/**").authenticated()
                .antMatchers("/plan/**").authenticated();

        //请求过滤器，必须要加上，否则无法鉴权
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //对于没有登录的用户，返回401
        http.exceptionHandling().authenticationEntryPoint(jwtUnauthorizedHandler);
        //对于登录了但是权限有问题的用户，返回403
        http.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);
    }

}

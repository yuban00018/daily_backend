package com.daily.controller;

import com.daily.model.response.Result;
import com.daily.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @Author:yuban00018
 * @Date:2022/2/6
 * @Description:
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/statistic")
public class StatisticController {
    @Resource
    StatisticService statisticService;

    @GetMapping("/recent")
    public Result getRecentRate(){
        return statisticService.getRecentRate();
    }
}

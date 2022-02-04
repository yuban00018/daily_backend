package com.daily.controller;

import com.daily.model.request.TodoCheckInfo;
import com.daily.model.response.Result;
import com.daily.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * program: com.daily.controller
 * description:日常计划有关的控制器
 *
 * @author: yuban00018
 * @version: 2022/2/2
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/plan")
public class PlanController {
    @Resource
    private PlanService planService;

    @PostMapping("/check")
    public Result check(@Validated @RequestBody TodoCheckInfo todoCheckInfo){
        return planService.check(todoCheckInfo);
    }
    @GetMapping("/list")
    public Result getList(Integer id){
        return planService.getList(id);
    }
}

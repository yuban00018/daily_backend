package com.daily.controller;


import com.daily.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import com.daily.model.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {
    @Resource
    GroupService groupService;

    @PostMapping ("/groupRecommend")
    public Result getGroupRecommend() {
        return groupService.getGroupRecommend();
    }

    @GetMapping ("/groupInfoById")
    public Result getGroupInfoById(Integer id) {
        log.info("id: " + id);
        return groupService.getGroupInfoById(id);
    }
}

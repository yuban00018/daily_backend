package com.daily.controller;


import com.daily.model.request.GroupInfo;
import com.daily.model.request.GroupPlanInfo;
import com.daily.model.request.UpdatePlanInfo;
import com.daily.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import com.daily.model.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Au_Miner
 * 2022-3-18
 */
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
        // log.info("id: " + id);
        return groupService.getGroupInfoById(id);
    }

    @GetMapping ("/groupInfoByKind")
    public Result getGroupInfoByKind(Integer kind) {
        return groupService.getGroupInfoByKind(kind);
    }

    @GetMapping ("/groupInfoByGroupId")
    public Result getGroupInfoByGroupId(Integer groupId) {
        // log.info("groupId: " + groupId);
        return groupService.getGroupInfoByGroupId(groupId);
    }

    @GetMapping ("/groupPlanByGroupId")
    public Result getGroupPlanByGroupId(Integer groupId) {
        // log.info("groupId: " + groupId);
        return groupService.getGroupPlanByGroupId(groupId);
    }

    @GetMapping ("/userJoinGroup")
    public Result userJoinGroup(Integer userId, Integer groupId) {
        // log.info("groupId: " + groupId);
        // log.info("userId: " + userId);
        return groupService.userJoinGroup(userId, groupId);
    }

    @GetMapping ("/userExitGroup")
    public Result userExitGroup(Integer userId, Integer groupId) {
        // log.info("groupId: " + groupId);
        // log.info("userId: " + userId);
        return groupService.userExitGroup(userId, groupId);
    }

    @GetMapping ("/userFailGroupPlan")
    public Result userFailGroupPlan(Integer userId, Integer planId) {
        // log.info("planId1: " + planId);
        // log.info("userId1: " + userId);
        return groupService.userFailGroupPlan(userId, planId);
    }

    @GetMapping ("/userDoGroupPlan")
    public Result userDoGroupPlan(Integer userId, Integer planId) {
        // log.info("planId2: " + planId);
        // log.info("userId2: " + userId);
        return groupService.userDoGroupPlan(userId, planId);
    }

    @GetMapping ("/userPossessGroupInfo")
    public Result userPossessGroupInfo(Integer userId) {
        // log.info("userId: " + userId);
        return groupService.userPossessGroupInfo(userId);
    }

    @GetMapping ("/userDelPossessGroup")
    public Result userDelPossessGroup(Integer userId, Integer groupId) {
        // log.info("groupId: " + groupId);
        // log.info("userId: " + userId);
        return groupService.userDelPossessGroup(userId, groupId);
    }

    @PostMapping("/createOrModifyGroupInfo")
    public Result createOrModifyGroupInfo(@Validated @RequestBody GroupInfo groupInfo){
        // log.info(String.valueOf(groupInfo));
        return groupService.createOrModifyGroupInfo(groupInfo);
    }

    @GetMapping ("/getPlanInfoByPlanId")
    public Result getPlanInfoByPlanId(Integer planId) {
        log.info("planId: " + planId);
        return groupService.getPlanInfoByPlanId(planId);
    }

    @GetMapping ("/userDelPlanInfo")
    public Result userDelPlanInfo(Integer userId, Integer planId) {
        log.info("planId: " + planId);
        log.info("userId: " + userId);
        return groupService.userDelPlanInfo(userId, planId);
    }

    @PostMapping("/createOrModifyPlanInfo")
    public Result createOrModifyPlanInfo(@Validated @RequestBody GroupPlanInfo groupPlanInfo){
        log.info(String.valueOf(groupPlanInfo));
        return groupService.createOrModifyPlanInfo(groupPlanInfo);
    }
}

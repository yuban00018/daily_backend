package com.daily.service;

import com.daily.model.request.GroupInfo;
import com.daily.model.request.GroupPlanInfo;
import com.daily.model.response.Result;

public interface GroupService {
    /***
     * Au_Miner
     * 2022-3-18
     * @return
     */
    Result getGroupRecommend();

    Result getGroupInfoById(Integer id);

    Result getGroupInfoByKind(Integer kind);

    Result getGroupInfoByGroupId(Integer groupId);

    Result getGroupPlanByGroupId(Integer groupId);

    Result userJoinGroup(Integer userId, Integer groupId);

    Result userExitGroup(Integer userId, Integer groupId);

    Result userDoGroupPlan(Integer userId, Integer planId);

    Result userFailGroupPlan(Integer userId, Integer planId);

    Result userPossessGroupInfo(Integer userId);

    Result userDelPossessGroup(Integer userId, Integer groupId);

    Result createOrModifyGroupInfo(GroupInfo groupInfo);

    // 管理员获取小组任务信息
    Result getPlanInfoByPlanId(Integer planId);

    Result userDelPlanInfo(Integer userId, Integer planId);

    Result createOrModifyPlanInfo(GroupPlanInfo groupPlanInfo);

    Result getGroupRank();
}

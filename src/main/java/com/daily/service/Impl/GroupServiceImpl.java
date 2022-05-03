package com.daily.service.Impl;

import com.daily.dao.daily.*;
import com.daily.exception.EmAllException;
import com.daily.model.entity.daily.*;
import com.daily.model.request.GroupInfo;
import com.daily.model.request.GroupPlanInfo;
import com.daily.model.response.GroupPlanResponse;
import com.daily.model.response.GroupResponse;
import com.daily.model.response.Result;
import com.daily.service.GroupService;
import com.daily.tools.AuthTool;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 * Au_Miner
 * 2022/3/18
 * Group Service层
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupInfoDoMapper groupInfoDoMapper;

    @Resource
    private UserGroupDoMapper userGroupDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private GroupPlanDoMapper groupPlanDoMapper;

    @Resource
    private AuthTool authTool;

    @Resource
    private UserPlanRecordDoMapper userPlanRecordDoMapper;

    @Override
    public Result getGroupRecommend() {
        GroupInfoDoExample groupDoExample = new GroupInfoDoExample();
        groupDoExample.createCriteria().getAllCriteria();
        List<GroupInfoDo> groupDoList = groupInfoDoMapper.selectByExample(groupDoExample);
        if (groupDoList.isEmpty()) {
            return ResultTool.success();
        }
        List<GroupResponse> resList = new ArrayList<>();
        int tmp = 0;
        for (GroupInfoDo groupInfoDo : groupDoList) {
            GroupResponse groupResponse = new GroupResponse();
            BeanUtils.copyProperties(groupInfoDo, groupResponse);
            resList.add(groupResponse);
            if (++tmp == 9)
                break;
        }
        return ResultTool.success(resList);
    }


    @Override
    public Result getGroupInfoById(Integer id) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserIdEqualTo(id);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        // log.info("当前用户正确");
        UserGroupDoExample userGroupDoExample = new UserGroupDoExample();
        userGroupDoExample.createCriteria().andUserIdEqualTo(id);
        // log.info("当前的id为：" + id);
        List<UserGroupDo> userGroupDos = userGroupDoMapper.selectByExample(userGroupDoExample);


        // // log.info("当前第一次查询长度：" + userGroupDos.toArray().length);
        // userGroupDoExample = new UserGroupDoExample();
        // userDoExample.createCriteria().getAllCriteria();
        // List<UserGroupDo> userGroupDos = userGroupDoMapper.selectByExample(userGroupDoExample);
        // for (UserGroupDo userGroupDo : userGroupDos) {
        //     log.info("当前userGroup表元素为：" + userGroupDo.getGroupId());
        // }
        // return ResultTool.success();


        if (userGroupDos.isEmpty()) {
            // log.info("当前为空");
            return ResultTool.success();
        }
        List<GroupResponse> res = new ArrayList<>();
        for (UserGroupDo userGroupDo : userGroupDos) {
            GroupInfoDoExample groupDoExample = new GroupInfoDoExample();
            groupDoExample.createCriteria().andGroupIdEqualTo(userGroupDo.getGroupId());
            List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupDoExample);
            for (GroupInfoDo groupInfoDo : groupInfoDos) {
                GroupResponse groupResponse = new GroupResponse();
                BeanUtils.copyProperties(groupInfoDo, groupResponse);
                res.add(groupResponse);
            }
        }
        return ResultTool.success(res);
    }


    @Override
    public Result getGroupInfoByKind(Integer kind) {
        GroupInfoDoExample groupDoExample = new GroupInfoDoExample();
        if (kind != 0)
            groupDoExample.createCriteria().andKindEqualTo(kind);
        else
            groupDoExample.createCriteria().getAllCriteria();
        List<GroupInfoDo> groupDoList = groupInfoDoMapper.selectByExample(groupDoExample);
        if (groupDoList.isEmpty()) {
            return ResultTool.success();
        }
        List<GroupResponse> resList = new ArrayList<>();
        for (GroupInfoDo groupInfoDo : groupDoList) {
            GroupResponse groupResponse = new GroupResponse();
            BeanUtils.copyProperties(groupInfoDo, groupResponse);
            resList.add(groupResponse);
        }
        return ResultTool.success(resList);
    }



    @Override
    public Result getGroupInfoByGroupId(Integer groupId) {
        GroupInfoDoExample groupDoExample = new GroupInfoDoExample();
        groupDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupInfoDo> groupDoList = groupInfoDoMapper.selectByExample(groupDoExample);
        if (groupDoList.isEmpty()) {
            return ResultTool.error(EmAllException.NO_SUCH_GROUP);
        }
        GroupResponse groupResponse = new GroupResponse();
        BeanUtils.copyProperties(groupDoList.get(0), groupResponse);

        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserIdEqualTo(groupResponse.getLeaderId());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty()) {
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        }
        groupResponse.setLeaderName(userDos.get(0).getName());
        return ResultTool.success(groupResponse);
    }


    @Override
    public Result getGroupPlanByGroupId(Integer groupId) {
        GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
        groupPlanDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupPlanDo> groupPlanDos = groupPlanDoMapper.selectByExample(groupPlanDoExample);
        List<GroupPlanResponse> res = new ArrayList<>();
        log.info("size: " + String.valueOf(groupPlanDos.size()));

        for (GroupPlanDo groupPlanDo: groupPlanDos) {
            GroupPlanResponse tmp = new GroupPlanResponse();
            BeanUtils.copyProperties(groupPlanDo, tmp);

            Integer planId = groupPlanDo.getPlanId();
            String userId = authTool.getUserId();
            UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
            userPlanRecordDoExample.createCriteria().andUserIdEqualTo(Integer.valueOf(userId)).andPlanIdEqualTo(planId);
            List<UserPlanRecordDo> userPlanRecordDos = userPlanRecordDoMapper.selectByExample(userPlanRecordDoExample);
            // log.info("size: " + userPlanRecordDos.size());
            // log.info("userId: " + userId);
            // log.info("userId2: " + authTool.getUserId());
            // log.info("planId: " + planId);
            if (userPlanRecordDos.isEmpty())
                tmp.setType("off");
            else
                tmp.setType(userPlanRecordDos.get(0).getType());

            res.add(tmp);
        }
        return ResultTool.success(res);
    }


    @Override
    public Result userJoinGroup(Integer userId, Integer groupId) {
        UserGroupDoExample userGroupDoExample = new UserGroupDoExample();
        userGroupDoExample.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
        List<UserGroupDo> userGroupDos = userGroupDoMapper.selectByExample(userGroupDoExample);
        if (userGroupDos.isEmpty()) {
            UserGroupDo userGroupDo = new UserGroupDo();
            userGroupDo.setGroupId(groupId);
            userGroupDo.setUserId(userId);
            if (userGroupDoMapper.insertSelective(userGroupDo) < 1) {
                return ResultTool.error(EmAllException.DATABASE_ERR);
            }
            return ResultTool.success();
        }
        else {
            return ResultTool.error(EmAllException.USER_HAVEN_JOINED_GROUP);
        }
    }


    @Override
    public Result userExitGroup(Integer userId, Integer groupId) {
        UserGroupDoExample userGroupDoExample = new UserGroupDoExample();
        userGroupDoExample.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
        if (userGroupDoMapper.deleteByExample(userGroupDoExample) > 0) {
            UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
            userPlanRecordDoExample.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
            userPlanRecordDoMapper.deleteByExample(userPlanRecordDoExample);
            return ResultTool.success();
        }
        else {
            return ResultTool.error(EmAllException.USER_NOT_JOINED_GROUP);
        }
    }


    @Override
    public Result userDoGroupPlan(Integer userId, Integer planId) {
        GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
        groupPlanDoExample.createCriteria().andPlanIdEqualTo(planId);
        List<GroupPlanDo> groupPlanDos = groupPlanDoMapper.selectByExample(groupPlanDoExample);
        if (groupPlanDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_PLAN);
        UserGroupDoExample userGroupDoExample = new UserGroupDoExample();
        userGroupDoExample.createCriteria().andGroupIdEqualTo(groupPlanDos.get(0).getGroupId()).andUserIdEqualTo(userId);
        List<UserGroupDo> userGroupDos = userGroupDoMapper.selectByExample(userGroupDoExample);
        if (userGroupDos.isEmpty())
            return ResultTool.error(EmAllException.USER_NOT_JOINED_GROUP);
        UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
        userPlanRecordDoExample.createCriteria().andUserIdEqualTo(userId).andPlanIdEqualTo(planId);
        List<UserPlanRecordDo> userPlanRecordDos = userPlanRecordDoMapper.selectByExample(userPlanRecordDoExample);
        UserPlanRecordDo userPlanRecordDo = new UserPlanRecordDo();
        userPlanRecordDo.setType("on");
        userPlanRecordDo.setPlanId(planId);
        userPlanRecordDo.setUserId(userId);
        userPlanRecordDo.setGroupId(groupPlanDos.get(0).getGroupId());
        if (userPlanRecordDos.isEmpty())
            userPlanRecordDoMapper.insertSelective(userPlanRecordDo);
        else
            userPlanRecordDoMapper.updateByPrimaryKeySelective(userPlanRecordDo);
        return ResultTool.success();
    }

    @Override
    public Result userFailGroupPlan(Integer userId, Integer planId) {
        UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
        userPlanRecordDoExample.createCriteria().andPlanIdEqualTo(planId).andUserIdEqualTo(userId);
        UserPlanRecordDo userPlanRecordDo = new UserPlanRecordDo();
        userPlanRecordDo.setType("off");
        if (userPlanRecordDoMapper.updateByExampleSelective(userPlanRecordDo, userPlanRecordDoExample) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        else
            return ResultTool.success();
    }


    @Override
    public Result userPossessGroupInfo(Integer userId) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
        groupInfoDoExample.createCriteria().andLeaderIdEqualTo(userId);
        List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
        List<GroupResponse> res = new ArrayList<>();
        for (GroupInfoDo groupInfoDo: groupInfoDos) {
            GroupResponse groupResponse = new GroupResponse();
            BeanUtils.copyProperties(groupInfoDo, groupResponse);
            res.add(groupResponse);
        }
        return ResultTool.success(res);
    }


    @Override
    public Result userDelPossessGroup(Integer userId, Integer groupId) {
        GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
        groupInfoDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
        if (groupInfoDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_GROUP);
        if (!Objects.equals(groupInfoDos.get(0).getLeaderId(), userId))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
        userPlanRecordDoExample.createCriteria().andGroupIdEqualTo(groupId);
        if (userPlanRecordDoMapper.deleteByExample(userPlanRecordDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        UserGroupDoExample userGroupDoExample = new UserGroupDoExample();
        userGroupDoExample.createCriteria().andGroupIdEqualTo(groupId);
        if (userGroupDoMapper.deleteByExample(userGroupDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
        groupPlanDoExample.createCriteria().andGroupIdEqualTo(groupId);
        if (groupPlanDoMapper.deleteByExample(groupPlanDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (groupInfoDoMapper.deleteByExample(groupInfoDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }


    @Override
    public Result createOrModifyGroupInfo(GroupInfo groupInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserIdEqualTo(groupInfo.getLeaderId());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);

        GroupInfoDo groupInfoDo = new GroupInfoDo();
        BeanUtils.copyProperties(groupInfo, groupInfoDo);
        if (groupInfo.getGroupId() == -1) {
            groupInfoDo.setGroupId(null);
            if (groupInfoDoMapper.insertSelective(groupInfoDo) < 1)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
            groupInfoDoExample.createCriteria().andGroupNameEqualTo(groupInfo.getGroupName());
            List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);

            UserGroupDo userGroupDo = new UserGroupDo();
            userGroupDo.setUserId(groupInfo.getLeaderId());
            userGroupDo.setGroupId(groupInfoDos.get(0).getGroupId());
            if (userGroupDoMapper.insertSelective(userGroupDo) < 1)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            return ResultTool.success();
        }
        else {
            GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
            groupInfoDoExample.createCriteria().andGroupIdEqualTo(groupInfo.getGroupId());
            List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
            if (groupInfoDos.isEmpty())
                return ResultTool.error(EmAllException.NO_SUCH_GROUP);
            if (!Objects.equals(groupInfoDos.get(0).getLeaderId(), groupInfo.getLeaderId()))
                return ResultTool.error(EmAllException.NOT_AUTHORIZED);
            if (groupInfoDoMapper.updateByPrimaryKeySelective(groupInfoDo) < 1)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            return ResultTool.success();
        }
    }


    @Override
    public Result getPlanInfoByPlanId(Integer planId) {
        Integer userId = Integer.valueOf(authTool.getUserId());
        GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
        groupPlanDoExample.createCriteria().andPlanIdEqualTo(planId);
        List<GroupPlanDo> groupPlanDos = groupPlanDoMapper.selectByExample(groupPlanDoExample);
        if (groupPlanDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_PLAN);
        Integer groupId = groupPlanDos.get(0).getGroupId();
        GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
        groupInfoDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
        if (groupInfoDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_GROUP);
        if (!Objects.equals(groupInfoDos.get(0).getLeaderId(), userId))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        GroupPlanResponse groupPlanResponse = new GroupPlanResponse();
        BeanUtils.copyProperties(groupPlanDos.get(0), groupPlanResponse);
        return ResultTool.success(groupPlanResponse);
    }


    @Override
    public Result userDelPlanInfo(Integer userId, Integer planId) {
        if (userId != Integer.parseInt(authTool.getUserId()))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
        groupPlanDoExample.createCriteria().andPlanIdEqualTo(planId);
        List<GroupPlanDo> groupPlanDos = groupPlanDoMapper.selectByExample(groupPlanDoExample);
        if (groupPlanDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_PLAN);
        Integer groupId = groupPlanDos.get(0).getGroupId();
        GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
        groupInfoDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
        if (groupInfoDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_GROUP);
        if (!Objects.equals(groupInfoDos.get(0).getLeaderId(), userId))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        UserPlanRecordDoExample userPlanRecordDoExample = new UserPlanRecordDoExample();
        userPlanRecordDoExample.createCriteria().andPlanIdEqualTo(planId);
        if (userPlanRecordDoMapper.deleteByExample(userPlanRecordDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (groupPlanDoMapper.deleteByExample(groupPlanDoExample) < 1)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }


    @Override
    public Result createOrModifyPlanInfo(GroupPlanInfo groupPlanInfo) {
        if (groupPlanInfo.getUserId() != Integer.parseInt(authTool.getUserId()))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        Integer userId = groupPlanInfo.getUserId();
        Integer groupId = groupPlanInfo.getGroupId();
        Integer planId = groupPlanInfo.getPlanId();
        GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
        groupInfoDoExample.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
        if (!Objects.equals(groupInfoDos.get(0).getLeaderId(), userId))
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        if (planId == -1) {
            GroupPlanDo groupPlanDo = new GroupPlanDo();
            BeanUtils.copyProperties(groupPlanInfo, groupPlanDo);
            if (groupPlanDoMapper.insertSelective(groupPlanDo) < 1)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            return ResultTool.success();
        }
        else {
            GroupPlanDoExample groupPlanDoExample = new GroupPlanDoExample();
            groupPlanDoExample.createCriteria().andPlanIdEqualTo(planId);
            List<GroupPlanDo> groupPlanDos = groupPlanDoMapper.selectByExample(groupPlanDoExample);
            if (!Objects.equals(groupId, groupPlanDos.get(0).getGroupId()))
                return ResultTool.error(EmAllException.NOT_AUTHORIZED);
            GroupPlanDo groupPlanDo = new GroupPlanDo();
            BeanUtils.copyProperties(groupPlanInfo, groupPlanDo);
            if (groupPlanDoMapper.updateByPrimaryKeySelective(groupPlanDo) < 1)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            return ResultTool.success();
        }
    }
}

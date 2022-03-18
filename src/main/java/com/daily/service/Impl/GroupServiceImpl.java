package com.daily.service.Impl;

import com.daily.dao.GroupInfoDoMapper;
import com.daily.dao.UserDoMapper;
import com.daily.dao.UserGroupDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.*;
import com.daily.model.response.GroupResponse;
import com.daily.model.response.Result;
import com.daily.service.GroupService;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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



    @Override
    public Result getGroupRecommend() {
        GroupInfoDoExample groupDoExample = new GroupInfoDoExample();
        groupDoExample.createCriteria().getAllCriteria();
        List<GroupInfoDo> groupDoList = groupInfoDoMapper.selectByExample(groupDoExample);
        if (groupDoList.isEmpty()) {
            return ResultTool.success();
        }
        List<GroupResponse> resList = new ArrayList<>();
        GroupResponse groupResponse = new GroupResponse();
        int tmp = 0;
        for (GroupInfoDo groupInfoDo : groupDoList) {
            BeanUtils.copyProperties(groupInfoDo, groupResponse);
            resList.add(groupResponse);
            if (++tmp == 12)
                break;
        }
        return ResultTool.success(resList);
    }


    @Override
    public Result getGroupInfoById(Integer id) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andIdEqualTo(id);
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
}

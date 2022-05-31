package com.daily.service.Impl;

import com.daily.dao.daily.GroupInfoDoMapper;
import com.daily.dao.daily.GroupRankDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.daily.*;
import com.daily.model.response.Result;
import com.daily.service.KafkaService;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.List;

/***
 * Au_Miner
 * 2022/3/26
 * Group Service层
 */
@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    @Resource
    private GroupRankDoMapper groupRankDoMapper;

    @Resource
    private GroupInfoDoMapper groupInfoDoMapper;

    @Override
    public Result getGroupRank(String str) {
        GroupRankDoExample groupRankDoExample = new GroupRankDoExample();
        groupRankDoExample.createCriteria().getAllCriteria();
        groupRankDoMapper.deleteByExample(groupRankDoExample);
        String[] arr = str.split(" ");
        GroupRankDo groupRankDo = new GroupRankDo();
        int[] arr1 = new int[10];
        int geshu = 0;
        for (int i = 0; i < arr.length; i++) {
            GroupInfoDoExample groupInfoDoExample = new GroupInfoDoExample();
            groupInfoDoExample.createCriteria().andGroupIdEqualTo(Integer.valueOf(arr[i]));
            List<GroupInfoDo> groupInfoDos = groupInfoDoMapper.selectByExample(groupInfoDoExample);
            if (groupInfoDos.isEmpty())
                continue;
            arr1[geshu++] = Integer.parseInt(arr[i]);
        }
        for (int i = 0; i < geshu; i++) {
            groupRankDo.setGroupId(arr1[i]);
            groupRankDo.setRnk(i + 1);
            // log.info("正在存: " + arr[i] + " " + (i + 1));
            if (groupRankDoMapper.insertSelective(groupRankDo) < 1) {
                return ResultTool.error(EmAllException.DATABASE_ERR);
            }
        }
        return ResultTool.success();
    }
}

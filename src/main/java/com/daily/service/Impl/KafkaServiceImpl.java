package com.daily.service.Impl;

import com.daily.dao.GroupRankDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.GroupRankDo;
import com.daily.model.entity.GroupRankDoExample;
import com.daily.model.response.Result;
import com.daily.service.KafkaService;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Result getGroupRank(String str) {
        GroupRankDoExample groupRankDoExample = new GroupRankDoExample();
        groupRankDoExample.createCriteria().getAllCriteria();
        groupRankDoMapper.deleteByExample(groupRankDoExample);
        String[] arr = str.split(" ");
        GroupRankDo groupRankDo = new GroupRankDo();
        for (int i = 0; i < arr.length; i++) {
            groupRankDo.setGroupId(Integer.parseInt(arr[i]));
            groupRankDo.setRnk(i + 1);
            log.info("正在存: " + arr[i] + " " + (i + 1));
            if (groupRankDoMapper.insertSelective(groupRankDo) < 1) {
                return ResultTool.error(EmAllException.DATABASE_ERR);
            }
        }
        return ResultTool.success();
    }
}

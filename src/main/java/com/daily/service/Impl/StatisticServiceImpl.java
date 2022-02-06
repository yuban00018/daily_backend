package com.daily.service.Impl;

import com.daily.dao.FailedTodoDoMapper;
import com.daily.model.entity.FailedTodoDo;
import com.daily.model.entity.FailedTodoDoExample;
import com.daily.model.response.RecentRateResponse;
import com.daily.model.response.Result;
import com.daily.service.StatisticService;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author:yuban00018
 * @Date:2022/2/6
 * @Description:
 */
@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    FailedTodoDoMapper failedTodoDoMapper;
    /**
     * @Author: yuban00018
     * @Date: 2022/2/6
     * @Return:
     * @Description: 最近失败记录（最多90条）
     */
    @Override
    public Result getRecentRate() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        FailedTodoDoExample failedTodoDoExample = new FailedTodoDoExample();
        failedTodoDoExample.setOrderByClause("faile_date desc");
        failedTodoDoExample.createCriteria()
                .andUserIdEqualTo(Integer.parseInt(userId));
        List<FailedTodoDo> failedTodoDoList = failedTodoDoMapper.selectByExample(failedTodoDoExample);
        List<RecentRateResponse> recentRateResponseList = new ArrayList<>();
        for(FailedTodoDo failedTodoDo: failedTodoDoList){
            RecentRateResponse recentRateResponse = new RecentRateResponse();
            recentRateResponse.setRate(failedTodoDo.getRate());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            recentRateResponse.setDate(formatter.format(failedTodoDo.getFaileDate()));
            recentRateResponseList.add(recentRateResponse);
        }
        return ResultTool.success(recentRateResponseList);
    }
}

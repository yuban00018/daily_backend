package com.daily.tools;


import com.daily.dao.daily.PlanDoMapper;
import com.daily.dao.daily.UserDoMapper;
import com.daily.model.entity.daily.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * @Author:yuban00018
 * @Date:2022/2/6
 * @Description:
 */
@Slf4j
@Component
public class FinishRateTool {
    @Resource
    com.daily.dao.daily.FailedTodoDoMapper failedTodoDoMapper;
    @Resource
    com.daily.dao.daily.PlanDoMapper planDoMapper;
    @Resource
    UserDoMapper userDoMapper;

    /**
     * @Author: yuban00018
     * @Date: 2022/2/6
     * @Return:
     * @Description: 定时任务，每天23:50更新全部用户的完成率
     */
    @Scheduled(cron = "0 50 23 * * ?")
    public void scheduledFinishRateUpdater() {
        Calendar cal = new GregorianCalendar();
        Integer day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) day = 7;
        Date today = new Date();
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().getAllCriteria();
        List<UserDo> userDoList = userDoMapper.selectByExample(userDoExample);
        if (userDoList == null) return;
        // 遍历用户
        for (UserDo userDo:userDoList) {
            PlanDoExample planDoExample = new PlanDoExample();
            planDoExample.createCriteria().andUserIdEqualTo(userDo.getUserId()).andTypeEqualTo("on");
            List<PlanDo> planDoList = planDoMapper.selectByExample(planDoExample);
            if (planDoList == null) return;
            int sum = 0;
            int finish = 0;
            // 遍历计划
            for (PlanDo planDo : planDoList) {
                if (planDo.getFrequency().contains(Integer.toString(day))) {
                    sum++;
                    if (planDo.getDone().equals("1")) finish++;
                }
                planDo.setDone("0");
                planDo.setLastDoneDate(today);
                planDoMapper.updateByPrimaryKeySelective(planDo);
            }
            // 更新用户经验
            long exp = userDo.getExp();
            exp += finish;
            userDo.setExp(exp);
            userDoMapper.updateByPrimaryKeySelective(userDo);
            // 如果当天没有计划认为完成率为100%
            if (sum == 0 && finish == 0) sum = finish = 1;
            // 计算完成率，小于1则记录
            if ((float) (finish * 100 / sum) < 100) {
                FailedTodoDo failedTodoDo = new FailedTodoDo();
                failedTodoDo.setFaileDate(today);
                failedTodoDo.setRate((float) (finish * 100 / sum));
                failedTodoDo.setUserId(userDo.getUserId());
                failedTodoDoMapper.insertSelective(failedTodoDo);
            }
        };
    }
}

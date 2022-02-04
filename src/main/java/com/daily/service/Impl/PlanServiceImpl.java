package com.daily.service.Impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.daily.dao.PlanDoMapper;
import com.daily.exception.EmAllException;
import com.daily.model.entity.PlanDo;
import com.daily.model.entity.PlanDoExample;
import com.daily.model.request.TodoCheckInfo;
import com.daily.model.response.PlanListResponse;
import com.daily.model.response.Result;
import com.daily.service.PlanService;
import com.daily.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * program: com.daily.service.Impl
 * description:日常计划服务的实现
 *
 * @author: yuban00018
 * @version: 2022/2/2
 */
@Slf4j
@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    PlanDoMapper planDoMapper;
    @Override
    public Result check(TodoCheckInfo todoCheckInfo) {
        PlanDo planDo = planDoMapper.selectByPrimaryKey(todoCheckInfo.getId());
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        if(planDo.getUserId().toString().equals(user_id)){
            planDo.setDone(todoCheckInfo.getDone());
            planDoMapper.updateByPrimaryKeySelective(planDo);
            return ResultTool.success();
        } else {
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        }
    }
    @Override
    public Result getList(Integer id){
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        if(id.toString().equals(user_id)) {
            PlanDoExample planDoExample = new PlanDoExample();
            planDoExample.createCriteria().andUserIdEqualTo(id).andTypeEqualTo("on");
            List<PlanDo> planDoList = planDoMapper.selectByExample(planDoExample);
            if(planDoList==null){
                return ResultTool.success();
            }
            Calendar cal = new GregorianCalendar();
            int day = cal.get(Calendar.DAY_OF_WEEK)-1;
            List<PlanListResponse> planList = new ArrayList<>();
            planDoList.forEach(planDo -> {
                if(planDo.getFrequency().contains(Integer.toString(day))){
                    PlanListResponse planListResponse = new PlanListResponse();
                    BeanUtils.copyProperties(planDo,planListResponse);
                    planList.add(planListResponse);
                }
            });
            return ResultTool.success(planList);
        } else {
            log.info(user_id+" "+id.toString());
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        }
    }
}

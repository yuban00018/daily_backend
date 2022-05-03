package com.daily.service.Impl;

import com.daily.dao.daily.*;
import com.daily.exception.EmAllException;
import com.daily.model.entity.daily.*;
import com.daily.model.request.PlanInfo;
import com.daily.model.request.UpdatePlanInfo;
import com.daily.model.response.Result;
import com.daily.service.PlanService;
import com.daily.tools.AuthTool;
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

    @Resource
    private AuthTool authTool;

    @Override
    public Result deletePlan(long id) {
        PlanDo planDo = planDoMapper.selectByPrimaryKey(id);
        String user_id = authTool.getUserId();
        if(planDo.getUserId().toString().equals(user_id)){
            planDoMapper.deleteByPrimaryKey(id);
            return ResultTool.success();
        } else {
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        }
    }

    @Override
    public Result add(PlanInfo planInfo) {
        PlanDo planDo = new PlanDo();
        planDo.setDone("0");
        planDo.setContent(planInfo.getContent());
        planDo.setFrequency(planInfo.getFrequency());
        planDo.setStartSince(new Date());
        planDo.setLastDoneDate(null);
        planDo.setType("on");
        planDo.setUserId(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
        planDoMapper.insertSelective(planDo);
        return ResultTool.success();
    }

    @Override
    public Result update(UpdatePlanInfo updatePlanInfo) {
        PlanDo planDo = planDoMapper.selectByPrimaryKey(updatePlanInfo.getPlanId());
        String user_id = authTool.getUserId();
        if(planDo.getUserId().toString().equals(user_id)){
            PlanDo newPlanDo = new PlanDo();
            newPlanDo.setPlanId(planDo.getPlanId());
            BeanUtils.copyProperties(updatePlanInfo,newPlanDo);
            planDoMapper.updateByPrimaryKeySelective(newPlanDo);
            return ResultTool.success();
        } else {
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        }
    }
    @Override
    public Result getList(Integer id) {
        String user_id = authTool.getUserId();
        if(id.toString().equals(user_id)) {
            PlanDoExample planDoExample = new PlanDoExample();
            planDoExample.createCriteria().andUserIdEqualTo(id);
            List<PlanDo> planDoList = planDoMapper.selectByExample(planDoExample);
            if(planDoList==null){
                return ResultTool.success();
            }
            return ResultTool.success(planDoList);
        } else {
            return ResultTool.error(EmAllException.NOT_AUTHORIZED);
        }
    }
}

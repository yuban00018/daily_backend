package com.daily.service;

import com.daily.model.request.TodoCheckInfo;
import com.daily.model.response.Result;

/**
 * program: com.daily.service
 * description:日常计划有关的服务
 *
 * @author: yuban00018
 * @version: 2022/2/2
 */
public interface PlanService {
    public Result check(TodoCheckInfo todoCheckInfo);
    public Result getList(Integer id);
}

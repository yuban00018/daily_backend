package com.daily.model.response;

import lombok.Data;

/**
 * program: com.daily.model.response
 * description:日常计划的返回内容
 *
 * @author: yuban00018
 * @version: 2022/2/2
 */
@Data
public class PlanListResponse {
    private Long planId;
    private String content;
    private String done;
}

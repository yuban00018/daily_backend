package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Au_Miner
 * 2022/3/20
 */
@Data
public class GroupPlanInfo {
    @NotNull(message="小组任务id不能为空")
    private Integer planId;

    @NotNull(message="小组id不能为空")
    private Integer groupId;

    @NotBlank(message="内容不能为空")
    private String content;

    @NotBlank(message="小组任务名不能为空")
    private String planName;

    @NotBlank(message="频率不能为空")
    private String frequency;

    @NotNull(message="用户id不能为空")
    private Integer userId;
}
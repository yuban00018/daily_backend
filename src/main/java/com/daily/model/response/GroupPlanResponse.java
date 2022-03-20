package com.daily.model.response;

import lombok.Data;


/**
 * Au_Miner
 * 2022-3-19
 * 用户-小组-任务-完成情况存储类
 * 管理员获取小组任务信息时，也用来承载（无type）
 */

@Data
public class GroupPlanResponse {
    private Integer planId;
    private Integer groupId;
    private String content;
    private String planName;
    private String frequency;
    private String type;
}

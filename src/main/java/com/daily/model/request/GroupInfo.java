package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Au_Miner
 * 2022/3/20
 */
@Data
public class GroupInfo {
    @NotNull(message="小组号不能为空")
    private Integer groupId;

    @NotNull(message="总经验不能为空")
    private Integer allexp;

    @NotNull(message="最近活跃度不能为空")
    private Integer recexp;

    @NotNull(message="管理员号不能为空")
    private Integer leaderId;

    @NotBlank(message="内容不能为空")
    private String content;

    @NotNull(message="成员数量不能为空")
    private Integer memberNumber;

    @NotNull(message="所属种类不能为空")
    private Integer kind;

    @NotBlank(message="小组名不能为空")
    private String groupName;
}

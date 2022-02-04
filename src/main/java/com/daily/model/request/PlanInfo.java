package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * program: com.daily.model.request
 * description:计划类型
 *
 * @author: yuban00018
 * @version: 2022/2/4
 */
@Data
public class PlanInfo {
    @NotBlank(message="内容不能为空")
    private String content;
    @NotBlank(message="频率不能为空")
    private String frequency;
}

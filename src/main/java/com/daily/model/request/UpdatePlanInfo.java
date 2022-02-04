package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * program: com.daily.model.request
 * description:
 *
 * @author: yuban00018
 * @version: 2022/2/4
 */
@Data
public class UpdatePlanInfo {
    @NotNull(message="id不为空")
    private long planId;
    @NotBlank(message="类型不能为空")
    private String type;
    @NotBlank(message="频率不能为空")
    private String frequency;
    @NotBlank(message="完成状态不能为空")
    private String done;
}

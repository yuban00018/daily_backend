package com.daily.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * program: com.daily.model.request
 * description:更改完成状态的请求体
 *
 * @author: yuban00018
 * @version: 2022/2/2
 */
@Data
public class TodoCheckInfo {
    @NotNull(message="待办id不能为空")
    private long id;
    @NotBlank(message="待办状态不能为空")
    private String done;
}

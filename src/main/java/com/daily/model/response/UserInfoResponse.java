package com.daily.model.response;

import lombok.Data;

import java.util.Date;

/**
 * program: com.daily.model.response
 * description:
 *
 * @author: yuban00018
 * @version: 2022/2/4
 */
@Data
public class UserInfoResponse {
    private Integer id;
    private String email;
    private long level;
    private long exp;
    private String type;
    private Date signUpDate;
    private String name;
    private String avatar;
}

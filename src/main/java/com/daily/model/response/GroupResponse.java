package com.daily.model.response;

import lombok.Data;

/***
 * Au_Miner
 * 2022-3-18
 */

@Data
public class GroupResponse {
    private Integer groupId;
    private Integer allexp;
    private Integer recexp;
    private Integer leaderId;
    private String content;
    private Integer memberNumber;
    private Integer kind;
    private String groupName;
}

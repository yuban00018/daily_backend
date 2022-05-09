package com.daily.model.response;

import lombok.Data;

/***
 * Au_Miner
 * 2022-3-18
 */

@Data
public class GroupRankResponse {
    private Integer rank;
    private String groupName;
    private Integer groupId;
    private String adminName;
    private Integer groupExp;
}

package com.daily.service;

import com.daily.model.response.Result;

public interface KafkaService {
    /***
     * Au_Miner
     * 2022-3-26
     * @return
     */
    Result getGroupRank(String str);
}

package com.daily.service;

import com.daily.model.response.Result;

public interface GroupService {
    /***
     * Au_Miner
     * 2022-3-18
     * @return
     */
    Result getGroupRecommend();

    Result getGroupInfoById(Integer id);
}

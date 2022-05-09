package com.daily.dao;

import com.daily.model.entity.GroupRankDo;
import com.daily.model.entity.GroupRankDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupRankDoMapper {
    int countByExample(GroupRankDoExample example);

    int deleteByExample(GroupRankDoExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupRankDo record);

    int insertSelective(GroupRankDo record);

    List<GroupRankDo> selectByExample(GroupRankDoExample example);

    GroupRankDo selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") GroupRankDo record, @Param("example") GroupRankDoExample example);

    int updateByExample(@Param("record") GroupRankDo record, @Param("example") GroupRankDoExample example);

    int updateByPrimaryKeySelective(GroupRankDo record);

    int updateByPrimaryKey(GroupRankDo record);
}
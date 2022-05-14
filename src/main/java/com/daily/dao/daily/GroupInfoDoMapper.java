package com.daily.dao.daily;

import com.daily.model.entity.daily.GroupInfoDo;
import com.daily.model.entity.daily.GroupInfoDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupInfoDoMapper {
    int countByExample(GroupInfoDoExample example);

    int deleteByExample(GroupInfoDoExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupInfoDo record);

    int insertSelective(GroupInfoDo record);

    List<GroupInfoDo> selectByExample(GroupInfoDoExample example);

    GroupInfoDo selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") GroupInfoDo record, @Param("example") GroupInfoDoExample example);

    int updateByExample(@Param("record") GroupInfoDo record, @Param("example") GroupInfoDoExample example);

    int updateByPrimaryKeySelective(GroupInfoDo record);

    int updateByPrimaryKey(GroupInfoDo record);
}
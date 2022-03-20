package com.daily.dao;

import com.daily.model.entity.UserPlanRecordDo;
import com.daily.model.entity.UserPlanRecordDoExample;
import com.daily.model.entity.UserPlanRecordDoKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPlanRecordDoMapper {
    int countByExample(UserPlanRecordDoExample example);

    int deleteByExample(UserPlanRecordDoExample example);

    int deleteByPrimaryKey(UserPlanRecordDoKey key);

    int insert(UserPlanRecordDo record);

    int insertSelective(UserPlanRecordDo record);

    List<UserPlanRecordDo> selectByExample(UserPlanRecordDoExample example);

    UserPlanRecordDo selectByPrimaryKey(UserPlanRecordDoKey key);

    int updateByExampleSelective(@Param("record") UserPlanRecordDo record, @Param("example") UserPlanRecordDoExample example);

    int updateByExample(@Param("record") UserPlanRecordDo record, @Param("example") UserPlanRecordDoExample example);

    int updateByPrimaryKeySelective(UserPlanRecordDo record);

    int updateByPrimaryKey(UserPlanRecordDo record);
}
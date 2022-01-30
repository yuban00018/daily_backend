package com.daily.dao;

import com.daily.model.entity.PlanDo;
import com.daily.model.entity.PlanDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanDoMapper {
    int countByExample(PlanDoExample example);

    int deleteByExample(PlanDoExample example);

    int deleteByPrimaryKey(Long planId);

    int insert(PlanDo record);

    int insertSelective(PlanDo record);

    List<PlanDo> selectByExample(PlanDoExample example);

    PlanDo selectByPrimaryKey(Long planId);

    int updateByExampleSelective(@Param("record") PlanDo record, @Param("example") PlanDoExample example);

    int updateByExample(@Param("record") PlanDo record, @Param("example") PlanDoExample example);

    int updateByPrimaryKeySelective(PlanDo record);

    int updateByPrimaryKey(PlanDo record);
}
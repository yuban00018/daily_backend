package com.daily.dao.daily;

import com.daily.model.entity.daily.GroupPlanDo;
import com.daily.model.entity.daily.GroupPlanDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupPlanDoMapper {
    int countByExample(GroupPlanDoExample example);

    int deleteByExample(GroupPlanDoExample example);

    int deleteByPrimaryKey(Integer planId);

    int insert(GroupPlanDo record);

    int insertSelective(GroupPlanDo record);

    List<GroupPlanDo> selectByExample(GroupPlanDoExample example);

    GroupPlanDo selectByPrimaryKey(Integer planId);

    int updateByExampleSelective(@Param("record") GroupPlanDo record, @Param("example") GroupPlanDoExample example);

    int updateByExample(@Param("record") GroupPlanDo record, @Param("example") GroupPlanDoExample example);

    int updateByPrimaryKeySelective(GroupPlanDo record);

    int updateByPrimaryKey(GroupPlanDo record);
}
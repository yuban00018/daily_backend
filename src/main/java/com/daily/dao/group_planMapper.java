package com.daily.dao;

import com.daily.model.entity.group_plan;
import com.daily.model.entity.group_planExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface group_planMapper {
    int countByExample(group_planExample example);

    int deleteByExample(group_planExample example);

    int deleteByPrimaryKey(Integer planId);

    int insert(group_plan record);

    int insertSelective(group_plan record);

    List<group_plan> selectByExample(group_planExample example);

    group_plan selectByPrimaryKey(Integer planId);

    int updateByExampleSelective(@Param("record") group_plan record, @Param("example") group_planExample example);

    int updateByExample(@Param("record") group_plan record, @Param("example") group_planExample example);

    int updateByPrimaryKeySelective(group_plan record);

    int updateByPrimaryKey(group_plan record);
}
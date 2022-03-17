package com.daily.dao;

import com.daily.model.entity.group;
import com.daily.model.entity.groupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface groupMapper {
    int countByExample(groupExample example);

    int deleteByExample(groupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(group record);

    int insertSelective(group record);

    List<group> selectByExample(groupExample example);

    group selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") group record, @Param("example") groupExample example);

    int updateByExample(@Param("record") group record, @Param("example") groupExample example);

    int updateByPrimaryKeySelective(group record);

    int updateByPrimaryKey(group record);
}
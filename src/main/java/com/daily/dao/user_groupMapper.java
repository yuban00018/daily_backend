package com.daily.dao;

import com.daily.model.entity.user_group;
import com.daily.model.entity.user_groupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface user_groupMapper {
    int countByExample(user_groupExample example);

    int deleteByExample(user_groupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(user_group record);

    int insertSelective(user_group record);

    List<user_group> selectByExample(user_groupExample example);

    user_group selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") user_group record, @Param("example") user_groupExample example);

    int updateByExample(@Param("record") user_group record, @Param("example") user_groupExample example);

    int updateByPrimaryKeySelective(user_group record);

    int updateByPrimaryKey(user_group record);
}
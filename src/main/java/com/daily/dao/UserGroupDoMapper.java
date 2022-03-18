package com.daily.dao;

import com.daily.model.entity.UserGroupDo;
import com.daily.model.entity.UserGroupDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGroupDoMapper {
    int countByExample(UserGroupDoExample example);

    int deleteByExample(UserGroupDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupDo record);

    int insertSelective(UserGroupDo record);

    List<UserGroupDo> selectByExample(UserGroupDoExample example);

    UserGroupDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGroupDo record, @Param("example") UserGroupDoExample example);

    int updateByExample(@Param("record") UserGroupDo record, @Param("example") UserGroupDoExample example);

    int updateByPrimaryKeySelective(UserGroupDo record);

    int updateByPrimaryKey(UserGroupDo record);
}
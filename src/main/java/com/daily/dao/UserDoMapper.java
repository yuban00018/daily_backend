package com.daily.dao;

import com.daily.model.entity.UserDo;
import com.daily.model.entity.UserDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDoMapper {
    int countByExample(UserDoExample example);

    int deleteByExample(UserDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}
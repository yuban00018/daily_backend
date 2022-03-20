package com.daily.dao;

import com.daily.model.entity.PasswordDo;
import com.daily.model.entity.PasswordDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PasswordDoMapper {
    int countByExample(PasswordDoExample example);

    int deleteByExample(PasswordDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PasswordDo record);

    int insertSelective(PasswordDo record);

    List<PasswordDo> selectByExample(PasswordDoExample example);

    PasswordDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PasswordDo record, @Param("example") PasswordDoExample example);

    int updateByExample(@Param("record") PasswordDo record, @Param("example") PasswordDoExample example);

    int updateByPrimaryKeySelective(PasswordDo record);

    int updateByPrimaryKey(PasswordDo record);
}
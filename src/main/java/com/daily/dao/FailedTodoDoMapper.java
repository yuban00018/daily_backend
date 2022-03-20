package com.daily.dao;

import com.daily.model.entity.FailedTodoDo;
import com.daily.model.entity.FailedTodoDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FailedTodoDoMapper {
    int countByExample(FailedTodoDoExample example);

    int deleteByExample(FailedTodoDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FailedTodoDo record);

    int insertSelective(FailedTodoDo record);

    List<FailedTodoDo> selectByExample(FailedTodoDoExample example);

    FailedTodoDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FailedTodoDo record, @Param("example") FailedTodoDoExample example);

    int updateByExample(@Param("record") FailedTodoDo record, @Param("example") FailedTodoDoExample example);

    int updateByPrimaryKeySelective(FailedTodoDo record);

    int updateByPrimaryKey(FailedTodoDo record);
}
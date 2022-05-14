package com.daily.dao.daily;

import com.daily.model.entity.daily.FailedTodoDo;
import com.daily.model.entity.daily.FailedTodoDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FailedTodoDoMapper {
    int countByExample(FailedTodoDoExample example);

    int deleteByExample(FailedTodoDoExample example);

    int deleteByPrimaryKey(Long failedId);

    int insert(FailedTodoDo record);

    int insertSelective(FailedTodoDo record);

    List<FailedTodoDo> selectByExample(FailedTodoDoExample example);

    FailedTodoDo selectByPrimaryKey(Long failedId);

    int updateByExampleSelective(@Param("record") FailedTodoDo record, @Param("example") FailedTodoDoExample example);

    int updateByExample(@Param("record") FailedTodoDo record, @Param("example") FailedTodoDoExample example);

    int updateByPrimaryKeySelective(FailedTodoDo record);

    int updateByPrimaryKey(FailedTodoDo record);
}
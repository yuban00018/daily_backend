package com.daily.dao.daily;

import com.daily.model.entity.daily.PasswordDo;
import com.daily.model.entity.daily.PasswordDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PasswordDoMapper {
    int countByExample(PasswordDoExample example);

    int deleteByExample(PasswordDoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(PasswordDo record);

    int insertSelective(PasswordDo record);

    List<PasswordDo> selectByExample(PasswordDoExample example);

    PasswordDo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") PasswordDo record, @Param("example") PasswordDoExample example);

    int updateByExample(@Param("record") PasswordDo record, @Param("example") PasswordDoExample example);

    int updateByPrimaryKeySelective(PasswordDo record);

    int updateByPrimaryKey(PasswordDo record);
}
package com.daily.dao.daily;

import com.daily.model.entity.daily.BlackListDo;
import com.daily.model.entity.daily.BlackListDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlackListDoMapper {
    int countByExample(BlackListDoExample example);

    int deleteByExample(BlackListDoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(BlackListDo record);

    int insertSelective(BlackListDo record);

    List<BlackListDo> selectByExample(BlackListDoExample example);

    BlackListDo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") BlackListDo record, @Param("example") BlackListDoExample example);

    int updateByExample(@Param("record") BlackListDo record, @Param("example") BlackListDoExample example);

    int updateByPrimaryKeySelective(BlackListDo record);

    int updateByPrimaryKey(BlackListDo record);
}
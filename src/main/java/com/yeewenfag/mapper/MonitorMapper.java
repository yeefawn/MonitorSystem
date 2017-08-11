package com.yeewenfag.mapper;

import com.yeewenfag.domain.Monitor;
import com.yeewenfag.domain.MonitorExample;
import com.yeewenfag.domain.MonitorWithBLOBs;
import java.util.List;

import com.yeewenfag.domain.vo.MonitorVo;
import org.apache.ibatis.annotations.Param;

public interface MonitorMapper {
    long countByExample(MonitorExample example);

    int deleteByExample(MonitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(MonitorWithBLOBs record);

    int insertSelective(MonitorWithBLOBs record);

    List<MonitorVo> selectByExampleWithBLOBs(MonitorExample example);

    List<Monitor> selectByExample(MonitorExample example);

    MonitorVo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MonitorWithBLOBs record, @Param("example") MonitorExample example);

    int updateByExampleWithBLOBs(@Param("record") MonitorWithBLOBs record, @Param("example") MonitorExample example);

    int updateByExample(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByPrimaryKeySelective(MonitorWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MonitorWithBLOBs record);

    int updateByPrimaryKey(Monitor record);
}
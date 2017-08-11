package com.yeewenfag.mapper;

import com.yeewenfag.domain.MonitorLogs;
import com.yeewenfag.domain.MonitorLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorLogsMapper {
    long countByExample(MonitorLogsExample example);

    int deleteByExample(MonitorLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MonitorLogs record);

    int insertSelective(MonitorLogs record);

    List<MonitorLogs> selectByExample(MonitorLogsExample example);

    MonitorLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MonitorLogs record, @Param("example") MonitorLogsExample example);

    int updateByExample(@Param("record") MonitorLogs record, @Param("example") MonitorLogsExample example);

    int updateByPrimaryKeySelective(MonitorLogs record);

    int updateByPrimaryKey(MonitorLogs record);
}
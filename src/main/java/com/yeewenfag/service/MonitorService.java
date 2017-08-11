package com.yeewenfag.service;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.vo.MonitorVo;

import java.util.List;

public interface MonitorService {

    /**
     * 新增被监控系统
     * @param monitor 新增内容
     * @throws Exception
     */
    void add(MonitorVo monitor) throws Exception;

    /**
     * 更新被监控系统信息
     * @param id 更新记录的id
     * @param monitor 更新内容
     * @throws Exception
     */
    void update(String id, MonitorVo monitor) throws Exception;

    /**
     * 删除被监控系统信息
     * @param id 删除记录的id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * 根据ID获取被监控系统信息
     * @param id 需要获取的记录主键id
     * @return 返回相应记录信息
     * @throws Exception
     */
    MonitorVo get(String id) throws Exception;

    /**
     * 分页查询被监控系统信息
     * @param monitor 查询条件，没有课传入null
     * @param pageNum 当前页面
     * @param pageSize 页面大小，查询全部可传入0
     * @return
     * @throws Exception
     */
    PageInfo<MonitorVo> query(MonitorVo monitor, int pageNum, int pageSize) throws Exception;
}

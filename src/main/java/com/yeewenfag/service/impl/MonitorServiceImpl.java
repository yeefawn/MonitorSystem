package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.MonitorExample;
import com.yeewenfag.domain.vo.MonitorVo;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.mapper.MonitorMapper;
import com.yeewenfag.service.MonitorService;
import com.yeewenfag.utils.CommonsUtils;
import com.yeewenfag.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    @Transactional
    public void add(MonitorVo monitor) throws Exception {
        if (monitor == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }
        if (monitor.getSystemName() == null || "".equals(monitor.getSystemName())){
            throw new MonitorException(ResultEnum.REQUIRE_NULL);
        }

        //生成主键
        String id = CommonsUtils.createId();
        monitor.setId(id);

        // 进行新增操作
        if (monitorMapper.insertSelective(monitor) <= 0) {
            throw new MonitorException(ResultEnum.INSERT_FAIL);
        }
    }

    @Override
    @Transactional
    public void update(String id, MonitorVo monitor) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }
        if (monitor == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }
        if (monitor.getSystemName() != null && monitor.getSystemName().equals("")) {
            throw new MonitorException(ResultEnum.REQUIRE_NULL);
        }

        monitor.setId(id);

        // 执行更新操作
        if (monitorMapper.updateByPrimaryKeySelective(monitor) <= 0) {
            throw new MonitorException(ResultEnum.UPDATE_FAIL);
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }

        // 执行删除操作
        if (monitorMapper.deleteByPrimaryKey(id) <= 0) {
            throw new MonitorException(ResultEnum.DELETE_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public MonitorVo get(String id) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }

        return monitorMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public PageInfo<MonitorVo> query(MonitorVo monitor, int pageNum, int pageSize) throws Exception {
        // 创建mybatis条件查询工具
        MonitorExample example = new MonitorExample();
        MonitorExample.Criteria criteria = example.createCriteria();

        // 设置条件
        if (monitor != null) {
            if (monitor.getSystemName() != null && !monitor.getSystemName().equals("")) {
                criteria.andSystemNameLike("%" + monitor.getSystemName() + "%");
            }
            if (monitor.getStatus() != null) {
                criteria.andStatusEqualTo(monitor.getStatus());
            }
        }

        example.or(criteria);

        // 使用Mybatis分页插件分页查询
        // 开启分页插件，并设置分页信息
        PageHelper.startPage(pageNum, pageSize);
        List<MonitorVo> list = monitorMapper.selectByExampleWithBLOBs(example);
        PageInfo page = new PageInfo(list);

        return page;
    }
}

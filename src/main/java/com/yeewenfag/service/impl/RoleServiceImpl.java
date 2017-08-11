package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Role;
import com.yeewenfag.domain.RoleExample;
import com.yeewenfag.domain.vo.RoleVo;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.mapper.RoleMapper;
import com.yeewenfag.service.RoleService;
import com.yeewenfag.utils.CommonsUtils;
import com.yeewenfag.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public PageInfo<RoleVo> query(String role, int pageNum, int pageSize) throws Exception {
        // 设置条件
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();

        if (role != null && !role.equals("")){
            criteria.andRoleLike("%" + role + "%");
        }
        //criteria.andAvailableEqualTo(1);

        example.or(criteria);
        example.setOrderByClause("create_time");

        PageHelper.startPage(pageNum, pageSize);
        List<RoleVo> list = roleMapper.selectByExample(example);
        PageInfo page = new PageInfo(list);

        return page;
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public RoleVo get(String id) throws Exception {
        if (id == null || id.equals("")){
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }

        RoleVo role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    @Transactional
    public void add(RoleVo roleVo) throws Exception {
        if (roleVo == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }
        if (roleVo.getRole() == null || roleVo.getRole().equals("")) {
            throw new MonitorException(ResultEnum.NAME_NOT_NULL);
        }

        // 创建ID
        String id = CommonsUtils.createId();
        roleVo.setId(id);

        if (roleVo.getResourceIds() != null && roleVo.getResourceIds().size() > 0) {
            if (roleMapper.batchInsertRoleAndResourceAssociation(roleVo) <= 0) {
                throw new MonitorException(ResultEnum.INSERT_FAIL);
            }
        }

        if (roleMapper.insertSelective(roleVo) <= 0) {
            throw new MonitorException(ResultEnum.INSERT_FAIL);
        }
    }

    @Override
    @Transactional
    public void update(String id, RoleVo roleVo) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }
        if (roleVo == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }
        if (roleVo.getRole() != null && roleVo.getRole().equals("")) {
            throw new MonitorException(ResultEnum.NAME_NOT_NULL);
        }
        roleVo.setId(id);
        // 删除角色与资源的关联关系
        roleMapper.dropRoleAndResourceAssociation(id);

        if (roleVo.getResourceIds() != null && roleVo.getResourceIds().size() > 0) {
            if (roleMapper.batchInsertRoleAndResourceAssociation(roleVo) <= 0) {
                throw new MonitorException(ResultEnum.UPDATE_FAIL);
            }
        }

        if (roleMapper.updateByPrimaryKeySelective(roleVo) <= 0) {
            throw new MonitorException(ResultEnum.UPDATE_FAIL);
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }
        // 删除角色与资源的关联关系
        roleMapper.dropRoleAndResourceAssociation(id);

        if (roleMapper.deleteByPrimaryKey(id) <= 0) {
            throw new MonitorException(ResultEnum.DELETE_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public List<Role> findAll() throws Exception {
        return roleMapper.selectAllRole();
    }
}

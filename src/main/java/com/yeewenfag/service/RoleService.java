package com.yeewenfag.service;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Role;
import com.yeewenfag.domain.vo.RoleVo;

import java.util.List;

public interface RoleService {

    /**
     * 分页查询角色表
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<RoleVo> query(String role, int pageNum, int pageSize) throws Exception;

    /**
     * 根据ID获取角色表相应记录
     * @param id
     * @return
     * @throws Exception
     */
    RoleVo get(String id) throws Exception;

    /**
     * 新增角色记录以及资源联系记录
     * @param roleVo
     * @throws Exception
     */
    void add(RoleVo roleVo) throws Exception;

    /**
     * 更新角色表记录以及资源联系记录
     * @param id
     * @param roleVo
     */
    void update(String id, RoleVo roleVo) throws Exception;

    /**
     * 根据id删除赢得角色表记录以及资源联系记录
     * @param id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * 获取所有的角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;
}

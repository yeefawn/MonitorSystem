package com.yeewenfag.service;


import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.Role;
import com.yeewenfag.domain.vo.ResourceVo;
import org.w3c.dom.ls.LSInput;

import java.util.List;

public interface ResourceService {

    /**
     * 分页查询资源列表
     * @param pageNum 查询页数
     * @param pageSize 页面大小
     * @return
     * @throws Exception
     */
    PageInfo<ResourceVo> query(ResourceVo resource, int pageNum, int pageSize) throws Exception;

    /**
     * 添加资源
     * @param resource
     * @return
     * @throws Exception
     */
    void add(Resource resource) throws Exception;

    /**
     * 更新资源
     * @param id
     * @param resource
     * @throws Exception
     */
    void update(Long id, ResourceVo resource) throws Exception;

    /**
     * 删除资源
     * @param id
     * @throws Exception
     */
    void delete(Long id) throws Exception;

    /**
     * 根据角色获取相应的菜单
     * @param role_id
     * @return
     * @throws Exception
     */
    List<Resource> findAllByRole(String role_id) throws Exception;

    /**
     * 获取所有的菜单
     * @return
     * @throws Exception
     */
    List<Resource> findAllMenu() throws Exception;

    /**
     * 根据主键获取相应记录
     * @param id
     * @return
     * @throws Exception
     */
    Resource get(Long id) throws Exception;

    /**
     * 根据角色获取资源树
     * @param role
     * @return
     * @throws Exception
     */
    List<ResourceVo> getMainMenuByRole(Role role) throws Exception;

    /**
     * 根据角色获取资源权限标识符
     * @param role_id
     * @return
     * @throws Exception
     */
    List<String> selectPermissionByRole(String role_id) throws Exception;
}

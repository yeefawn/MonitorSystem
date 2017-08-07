package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.ResourceExample;
import com.yeewenfag.domain.vo.ResourceVo;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.mapper.ResourceMapper;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public PageInfo<ResourceVo> query(int pageNum, int pageSize) throws Exception {
        // 为扩展条件准备
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();

        // 设置查询条件
        criteria.andAvailableEqualTo(1);
        example.setOrderByClause("priority");

        example.or(criteria);
        PageHelper.startPage(pageNum, pageSize);
        List<ResourceVo> list = resourceMapper.selectWithParentByExample(example);
        PageInfo<ResourceVo> page = new PageInfo<>(list);
        return page;
    }

    @Override
    @Transactional
    public void add(Resource resource) throws Exception {
        if (resource == null) {
            // TODO 抛出自定义错误
            throw new MonitorException(ResultEnum.ADD_DATA_NULL);
        }
        if (resource.getName() != null && !resource.getName().equals("")){
            // TODO 抛出自定义错误
            throw new MonitorException(ResultEnum.REQUIRE_NULL);
        }
        // TODO 增加权限标识格式校验
        if (resource.getPermission() != null) {
            // TODO 抛出自定义错误
            throw new MonitorException(ResultEnum.REQUIRE_NULL);
        }
        if (resource.getParentId() != null && !resource.getParentId().equals("")) {
            Resource parent = resourceMapper.selectByPrimaryKey(resource.getParentId());
            if (parent != null) {
                String parentIds = parent.getParentIds();
                if (parentIds == null || parentIds.equals("")){
                    resource.setType(resource.getType() + '1');
                    resource.setParentIds(parent.getId().toString());
                } else {
                    int parentNum = parentIds.split("/").length;
                    resource.setType(resource.getType() + (parentNum + 1));
                    resource.setParentIds(parentIds + "/" + parent.getId().toString());
                }
            } else {
                // TODO 抛出自定义错误
                throw new MonitorException(ResultEnum.DATA_NOT_FOUND);
            }
        } else {
          resource.setType(resource.getType() + '0');
        }
        // TODO 根据身份信息设置创建者
        resource.setCreateUser("admin");
        if (resourceMapper.insertSelective(resource) <= 0){
            // TODO 抛出自定义错误
            throw new MonitorException(ResultEnum.INSERT_FAIL);
        }

    }

    @Override
    @Transactional
    public void update(Long id, ResourceVo resource) throws Exception {
        if (id == null) {

        }
        if (resource == null) {

        }
        resource.setId(id);
        if (resourceMapper.updateByPrimaryKeySelective(resource) <= 0){

        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        if (id == null) {

        }
        if (resourceMapper.deleteByPrimaryKey(id) <= 0){

        }
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public List<Resource> findAllByRole(String role_id) throws Exception {
        if (role_id == null || role_id.equals("")) {

        }
        return null;
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public List<Resource> findAllMenu() throws Exception {
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();

        // 查找资源类型为'0*'的资源
        criteria.andTypeLike("0_");
        example.or(criteria);
        example.setOrderByClause("priority");

        return resourceMapper.selectByExample(example);
    }
}

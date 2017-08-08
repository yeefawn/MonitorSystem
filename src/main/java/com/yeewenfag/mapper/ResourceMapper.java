package com.yeewenfag.mapper;

import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.ResourceExample;
import com.yeewenfag.domain.vo.ResourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    List<ResourceVo> selectWithChildrenByExample(ResourceExample example);

    List<ResourceVo> selectWithParentByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<ResourceVo> getMainMenuByRole(String role_id);
}
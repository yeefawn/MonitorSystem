package com.yeewenfag.mapper;

import com.yeewenfag.domain.Role;
import com.yeewenfag.domain.RoleExample;
import com.yeewenfag.domain.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    List<RoleVo> selectByExample(RoleExample example);

    List<Role> selectAllRole();

    RoleVo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int batchInsertRoleAndResourceAssociation(RoleVo roleVo);

    int dropRoleAndResourceAssociation(String role_id);
}
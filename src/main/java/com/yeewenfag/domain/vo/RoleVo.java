package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.Role;

import java.util.List;

public class RoleVo extends Role {

    private String createUserName;

    private String modifyUserName;

    private List<ResourceVo> resources;

    private List<String> resourceIds;

    private String currentUser;

    public List<ResourceVo> getResources() {
        return resources;
    }

    public void setResources(List<ResourceVo> resources) {
        this.resources = resources;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

}


package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.Resource;

import java.util.List;

public class ResourceVo extends Resource {

    private List<ResourceVo> submenu;

    private Resource parent;

    private String createUserName;

    private String modifyUserName;

    public List<ResourceVo> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<ResourceVo> submenu) {
        this.submenu = submenu;
    }

    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
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
}

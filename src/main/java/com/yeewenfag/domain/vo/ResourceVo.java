package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.Resource;

import java.util.List;

public class ResourceVo extends Resource {

    private List<ResourceVo> submenu;

    private Resource parent;

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
}

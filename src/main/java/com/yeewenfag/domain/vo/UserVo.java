package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.User;

/**
 * @author 发
 * @create 2017-08-06 21:37
 */
public class UserVo extends User {

    // 角色名称
    private String role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}

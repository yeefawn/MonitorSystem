package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.Role;
import com.yeewenfag.domain.User;

/**
 * @author 发
 * @create 2017-08-06 21:37
 */
public class UserVo extends User {

    // 角色名称
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

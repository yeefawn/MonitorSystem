package com.yeewenfag.service;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;

/**
 * @author 发
 * @create 2017-08-06 22:23
 */
public interface UserService {

    /**
     * 分页查询用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> query(int pageNum, int pageSize);
}

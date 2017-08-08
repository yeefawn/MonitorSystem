package com.yeewenfag.service;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;
import com.yeewenfag.domain.vo.UserVo;

/**
 * @author 发
 * @create 2017-08-06 22:23
 */
public interface UserService {

    /**
     * 分页查询用户列表
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserVo> query(UserVo user, int pageNum, int pageSize) throws Exception;

    /**
     * 新增用户
     * @param userVo
     * @throws Exception
     */
    void add(UserVo userVo) throws Exception;
}

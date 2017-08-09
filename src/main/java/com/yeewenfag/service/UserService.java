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

    /**
     * 根据id获取相应记录
     * @param id
     * @return
     * @throws Exception
     */
    UserVo get(String id) throws Exception;

    /**
     * 更新用户
     * @param id
     * @param user
     * @throws Exception
     */
    void update(String id, UserVo user) throws Exception;

    /**
     * 删除id对应的用户记录
     * @param id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * 根据用户名获取相应的记录
     * @param username
     * @return
     * @throws Exception
     */
    UserVo selectByUsername(String username) throws Exception;

    /**
     * 更新统计信息
     * @param id
     * @param user
     * @throws Exception
     */
    void updateStatisticsMessage(String id, User user) throws Exception;
}

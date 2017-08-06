package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;
import com.yeewenfag.domain.UserExample;
import com.yeewenfag.mapper.UserMapper;
import com.yeewenfag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 发
 * @create 2017-08-06 22:26
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    // timeout单位为秒
    @Transactional(readOnly = true, timeout = 120)
    public PageInfo<User> query(int pageNum, int pageSize) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        example.or(criteria);
        /*Page<User> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userMapper.selectWithRoleNameByExample(example));*/
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectWithRoleNameByExample(example);
        PageInfo<User> page = new PageInfo<>(list);
        return page;
    }
}

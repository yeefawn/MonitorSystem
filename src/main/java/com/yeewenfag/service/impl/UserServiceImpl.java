package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;
import com.yeewenfag.domain.UserExample;
import com.yeewenfag.domain.vo.UserVo;
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
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    // timeout单位为秒
    @Transactional(readOnly = true, timeout = 120)
    public PageInfo<UserVo> query(UserVo user, int pageNum, int pageSize) throws Exception {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        // 设置条件
        if (user != null) {
            if (user.getUsername() != null && !user.getUsername().equals("")) {
                criteria.andUsernameEqualTo("%" + user.getUsername() + "%");
            }
            if (user.getFullname() != null && !user.getFullname().equals("")) {
                criteria.andFullnameEqualTo("%" + user.getFullname() + "%");
            }
            if (user.getEmail() != null && !user.getEmail().equals("")) {
                criteria.andEmailEqualTo("%" + user.getEmail() + "%");
            }
            if (user.getTelephone() != null && !user.getTelephone().equals("")) {
                criteria.andTelephoneEqualTo("%" + user.getTelephone() + "%");
            }
        }

        example.or(criteria);
        /*Page<User> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userMapper.selectWithRoleNameByExample(example));*/
        PageHelper.startPage(pageNum, pageSize);
        List<UserVo> list = userMapper.selectWithRoleByExample(example);
        PageInfo<UserVo> page = new PageInfo<>(list);
        return page;
    }

    @Override
    @Transactional
    public void add(UserVo userVo) throws Exception {

    }
}

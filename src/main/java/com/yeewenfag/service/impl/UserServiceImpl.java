package com.yeewenfag.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;
import com.yeewenfag.domain.UserExample;
import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.mapper.UserMapper;
import com.yeewenfag.service.UserService;
import com.yeewenfag.utils.CommonsUtils;
import com.yeewenfag.utils.ResultEnum;
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
        // 检查必要信息
        checkRequire(userVo);
        if (userVo.getPassword() == null || "".equals(userVo.getPassword())){
            throw  new MonitorException(ResultEnum.PASSWORD_IS_NULL);
        }

        // 生成id
        String id = CommonsUtils.createId();
        userVo.setId(id);

        // 加密密码
        userVo.setPassword(CommonsUtils.encodePassword(userVo.getPassword(), id));

        // 新增角色与用户关系记录
        if (userMapper.insertAssociationByUserAndRole(userVo) <= 0) {
            throw  new MonitorException(ResultEnum.INSERT_FAIL);
        }

        // 新增用户记录
        if (userMapper.insertSelective(userVo) <= 0) {
            throw  new MonitorException(ResultEnum.INSERT_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public UserVo get(String id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void update(String id, UserVo user) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }
        checkRequire(user);

        user.setId(id);

        if (user.getPassword() != null) {
            if (user.getPassword().equals("")) {
                user.setPassword(null);
            } else {
                // 加密密码
                user.setPassword(CommonsUtils.encodePassword(user.getPassword(), id));
            }
        }

        // 更新角色关联关系
        userMapper.updateAssociationByUserAndRole(user);

        // 更新用户
        if (userMapper.updateByPrimaryKeySelective(user) <= 0) {
            throw  new MonitorException(ResultEnum.UPDATE_FAIL);
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }

        // 删除用户角色关联关系
        if (userMapper.deleteAssociationByUserAndRole(id) <= 0) {
            throw  new MonitorException(ResultEnum.DELETE_FAIL);
        }

        // 删除用户
        if (userMapper.deleteByPrimaryKey(id) <= 0) {
            throw  new MonitorException(ResultEnum.DELETE_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true, timeout = 120)
    public UserVo selectByUsername(String username) throws Exception {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateStatisticsMessage(String id, User user) throws Exception {
        if (id == null || id.equals("")) {
            throw new MonitorException(ResultEnum.PRIMARYKEY_NULL);
        }
        if (user == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }

        user.setId(id);

        // 更新用户
        if (userMapper.updateByPrimaryKeySelective(user) <= 0) {
            throw  new MonitorException(ResultEnum.UPDATE_FAIL);
        }
    }

    private void checkRequire(UserVo user) throws Exception {
        if (user == null) {
            throw new MonitorException(ResultEnum.DATA_NULL);
        }
        if (user.getUsername() == null || "".equals(user.getUsername())){
            throw  new MonitorException(ResultEnum.USERNAME_IS_NULL);
        }
        if (user.getFullname() == null || "".equals(user.getFullname())){
            throw  new MonitorException(ResultEnum.REQUIRE_NULL);
        }
        if (user.getRole().getId() == null || "".equals(user.getRole().getId())){
            throw  new MonitorException(ResultEnum.ROLE_IS_NULL);
        }
    }
}

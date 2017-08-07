package com.yeewenfag.service.impl;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.User;
import com.yeewenfag.service.UserService;
import com.yeewenfag.utils.property.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void query() throws Exception {
        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));
        PageInfo<User> page =  userService.query(pageNum, pageNum);
        //Assert.assertEquals(0, page.getSize());
    }

}
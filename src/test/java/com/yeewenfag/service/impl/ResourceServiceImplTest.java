package com.yeewenfag.service.impl;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.vo.ResourceVo;
import com.yeewenfag.service.ResourceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class ResourceServiceImplTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void query() throws Exception {
        int pageNum = 1;
        int pageSize = 0;
        PageInfo page = resourceService.query(pageNum, pageSize);
        //Assert.assertEquals(2, page.getSize());
        Resource parent = ((ResourceVo)page.getList().get(1)).getParent();
        //Assert.assertEquals(new Long(1), parent.getId());
    }

    //@Test
    //public void add() throws Exception {
    //    ResourceVo resource = new ResourceVo();
    //    resource.setName("删除菜单");
    //    resource.setPriority(4);
    //    resource.setParentId(3L);
    //    resource.setParentIds("1/3");
    //    resource.setPermission("resource:delete");
    //    //resource.setUrl("/resource/list");
    //    resource.setCreateUser("admin");
    //    resource.setType("10");
    //    resourceService.add(resource);
    //}

}
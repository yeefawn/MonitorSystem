package com.yeewenfag.controller;

import com.github.pagehelper.PageInfo;
import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.utils.property.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        // 设置分页参数
        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));
        // 查询资源列表并把值放入request域
        model.addAttribute("page", resourceService.query(pageNum,pageSize));

        return "resource/list";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {

        // 获取所有已有的菜单列表并存入request域
        model.addAttribute("menus", resourceService.findAllMenu());

        return "resource/add";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Resource resource) throws Exception {

        resourceService.add(resource);

        return null;
    }
}

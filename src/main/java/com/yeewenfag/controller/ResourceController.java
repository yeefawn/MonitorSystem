package com.yeewenfag.controller;

import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.vo.ResourceVo;
import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import com.yeewenfag.utils.property.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Resource resource) throws Exception {

        resourceService.add(resource);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") Long id, Model model) throws Exception {

        // 获取相应的记录并存入request域
        model.addAttribute("resource", resourceService.get(id));
        // 获取所有已有的菜单列表并存入request域
        model.addAttribute("menus", resourceService.findAllMenu());

        return "/resource/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(Long id, ResourceVo resource) throws Exception {

        resourceService.update(id, resource);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") Long id) throws Exception {

        resourceService.delete(id);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }
}

package com.yeewenfag.controller;

import com.yeewenfag.domain.Resource;
import com.yeewenfag.domain.vo.ResourceVo;
import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import com.yeewenfag.utils.property.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        model.addAttribute("page", resourceService.query(null, pageNum,pageSize));
        model.addAttribute("typeMapping", createTypeMapping());

        return "resource/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String query(@ModelAttribute("condition") ResourceVo condition, Integer pageNum, Model model) throws Exception {
        // 设置分页参数
        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));
        // 查询资源列表并把值放入request域
        model.addAttribute("page", resourceService.query(condition, pageNum,pageSize));
        model.addAttribute("typeMapping", createTypeMapping());

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

        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        resource.setCreateUser(current.getId());

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
        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        resource.setModifyUser(current.getId());
        resource.setModifyTime(new Date());

        resourceService.update(id, resource);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") Long id) throws Exception {

        resourceService.delete(id);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    private Map<String, String> createTypeMapping() {
        Map<String, String> typeMapping = new HashMap<>();
        typeMapping.put("00", "主菜单");
        typeMapping.put("01", "一级菜单");
        typeMapping.put("02", "二级菜单");
        typeMapping.put("03", "三级菜单");
        typeMapping.put("04", "四级菜单");
        typeMapping.put("05", "五级菜单");
        typeMapping.put("06", "六级菜单");
        typeMapping.put("07", "七级菜单");
        typeMapping.put("08", "八级菜单");
        typeMapping.put("09", "九级菜单");
        typeMapping.put("10", "操作");
        return typeMapping;
    }

}

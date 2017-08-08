package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.domain.vo.RoleVo;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.service.RoleService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import com.yeewenfag.utils.property.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {

        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", roleService.query(null, pageNum, pageSize));

        return "/role/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(@ModelAttribute("role") String role, Integer pageNum, Model model) throws Exception {

        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", roleService.query(role, pageNum, pageSize));

        return "/role/list";
    }

    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") String id, Model model) throws Exception {
        // TODO 获取登录用户角色ID
        model.addAttribute("resources", resourceService.getMainMenuByRole(null));
        model.addAttribute("role", roleService.get(id));

        return "/role/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(String id, RoleVo roleVo, String[] resource_ids) throws Exception {

        // TODO 获取登录用户id
        roleVo.setModifyUser("admin");
        roleVo.setModifyTime(new Date());

        if (resource_ids != null && resource_ids.length > 0) {
            roleVo.setResourceIds(Arrays.asList(resource_ids));
        }

        roleService.update(id, roleVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {
        // TODO 获取登录用户角色ID

        model.addAttribute("resources", resourceService.getMainMenuByRole(null));

        return "/role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(RoleVo roleVo, String[] resource_ids) throws Exception {

        // TODO 获取登录用户id
        roleVo.setCreateUser("admin");

        if (resource_ids != null && resource_ids.length > 0) {
            roleVo.setResourceIds(Arrays.asList(resource_ids));
        }

        roleService.add(roleVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") String id) throws Exception {

        roleService.delete(id);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }
}

package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.domain.vo.RoleVo;
import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.service.RoleService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import com.yeewenfag.utils.property.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {

        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", roleService.query(null, pageNum, pageSize));

        return "/role/list";
    }

    @RequiresPermissions("role:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(@ModelAttribute("role") String role, Integer pageNum, Model model) throws Exception {

        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", roleService.query(role, pageNum, pageSize));

        return "/role/list";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") String id, Model model) throws Exception {
        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("resources", resourceService.getMainMenuByRole(current.getRole().getId()));
        model.addAttribute("role", roleService.get(id));

        return "/role/edit";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(String id, RoleVo roleVo, String[] resource_ids) throws Exception {

        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        roleVo.setModifyUser(current.getId());
        roleVo.setModifyTime(new Date());

        if (resource_ids != null && resource_ids.length > 0) {
            roleVo.setResourceIds(Arrays.asList(resource_ids));
        }

        roleService.update(id, roleVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequiresPermissions("role:add")
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {
        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();

        model.addAttribute("resources", resourceService.getMainMenuByRole(current.getRole().getId()));

        return "/role/add";
    }

    @RequiresPermissions("role:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(RoleVo roleVo, String[] resource_ids) throws Exception {

        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        roleVo.setCreateUser(current.getId());

        if (resource_ids != null && resource_ids.length > 0) {
            roleVo.setResourceIds(Arrays.asList(resource_ids));
        }

        roleService.add(roleVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") String id) throws Exception {

        roleService.delete(id);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }
}

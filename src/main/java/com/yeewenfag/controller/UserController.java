package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.RoleService;
import com.yeewenfag.service.UserService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import com.yeewenfag.utils.property.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author 发
 * @create 2017-08-06 21:31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception{

        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", userService.query(null, pageNum, pageSize));

        return "user/list";
    }

    @RequiresPermissions("user:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(UserVo userVo, Integer pageNum, Model model) throws Exception{

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", userService.query(userVo, pageNum, pageSize));

        return "user/list";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {

        model.addAttribute("roles", roleService.findAll());

        return "user/add";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(UserVo userVo) throws Exception {
        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        userVo.setCreateUser(current.getId());

        userService.add(userVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequiresPermissions("user:edit")
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") String id, Model model) throws Exception {

        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.findAll());

        return "user/edit";
    }

    @RequiresPermissions("user:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(String id, UserVo userVo) throws Exception {
        UserVo current = (UserVo) SecurityUtils.getSubject().getPrincipal();
        userVo.setModifyUser(current.getId());
        userVo.setModifyTime(new Date());

        userService.update(id, userVo);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") String id) throws Exception {

        userService.delete(id);

        return ResultUtils.success(ResultEnum.PROCESS_SUCCESS, null);
    }
}

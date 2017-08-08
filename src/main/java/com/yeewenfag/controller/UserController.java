package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.RoleService;
import com.yeewenfag.service.UserService;
import com.yeewenfag.utils.property.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception{

        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", userService.query(null, pageNum, pageSize));

        return "user/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(UserVo userVo, Integer pageNum, Model model) throws Exception{

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", userService.query(userVo, pageNum, pageSize));

        return "user/list";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {

        model.addAttribute("roles", roleService.findAll());

        return "user/add";
    }
}

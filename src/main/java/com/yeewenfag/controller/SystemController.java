package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.utils.ResultEnum;
import com.yeewenfag.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 发
 * @create 2017-08-06 20:54
 */
@Controller
public class SystemController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        UserVo user = (UserVo) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("roleName",user.getRole().getRole());
        model.addAttribute("fullName",user.getFullname());
        // 根据权限获取动态菜单
        model.addAttribute("menus", resourceService.getMainMenuByRole(user.getRole().getId()));
        return "system/index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) throws Exception{
        UserVo user = (UserVo) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("lastLoginIp",user.getLastLoginIp());
        model.addAttribute("lastLoginTime",user.getLastLoginTime());
        model.addAttribute("loginCount",user.getLoginCount());
        return "system/welcome";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) throws Exception {
        String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
        if(shiroLoginFailure!=null){
            if (UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
                //最终会抛给异常处理器
                request.setAttribute("result", ResultUtils.error(ResultEnum.ACCOUNT_NOT_FOUND));
            } else if (IncorrectCredentialsException.class.getName().equals(
                    shiroLoginFailure)) {
                request.setAttribute("result", ResultUtils.error(ResultEnum.MESSAGE_INCORRECT));
            } else if("validateCode:error".equals(shiroLoginFailure)){
                request.setAttribute("result", ResultUtils.error(ResultEnum.VALIDATE_INCORRECT));
            }else {
                request.setAttribute("result", ResultUtils.error(ResultEnum.UNKONW_ERROR));
            }
        }
        return "system/login";
    }
}

package com.yeewenfag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 发
 * @create 2017-08-06 20:54
 */
@Controller
public class SystemController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() throws Exception {
        return "system/index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() throws Exception{
        return "system/welcome";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) throws Exception {
        String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");

        return "system/login";
    }
}

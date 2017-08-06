package com.yeewenfag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 发
 * @create 2017-08-06 20:54
 */
@Controller
public class SystemController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "system/index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        return "system/welcome";
    }
}

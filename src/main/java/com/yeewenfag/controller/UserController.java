package com.yeewenfag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 发
 * @create 2017-08-06 21:31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        return "user/list";
    }
}

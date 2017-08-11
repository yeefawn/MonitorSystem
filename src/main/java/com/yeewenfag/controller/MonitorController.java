package com.yeewenfag.controller;

import com.yeewenfag.domain.vo.MonitorVo;
import com.yeewenfag.service.MonitorService;
import com.yeewenfag.utils.property.PropertyUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @RequiresPermissions("monitor:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        int pageNum = 1;
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", monitorService.query(null, pageNum, pageSize));

        return "monitor/list";
    }

    @RequiresPermissions("monitor:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(MonitorVo monitor, Integer pageNum, Model model) throws Exception {
        // 设置分页参数
        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        int pageSize = new Integer(PropertyUtils.getProperty("sys.defaultPageSize"));

        model.addAttribute("page", monitorService.query(monitor, pageNum, pageSize));

        return "monitor/list";
    }
}

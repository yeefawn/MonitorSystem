package com.yeewenfag.controller.handler;


import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MonitorExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handler(Exception e) {
        if (e instanceof MonitorException) {
            MonitorException monitorException = (MonitorException) e;
            return ResultUtils.error(monitorException.getCode(), monitorException.getMessage());
        } else {
            return ResultUtils.error(100, "未知错误");
        }
    }
}

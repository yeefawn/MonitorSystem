package com.yeewenfag.controller.handler;


import com.yeewenfag.domain.vo.Result;
import com.yeewenfag.exception.MonitorException;
import com.yeewenfag.utils.ResultUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MonitorExceptionHandler{

    private static Logger logger = LogManager.getLogger("MonitorExceptionHandler.class");

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handler(Exception e) {
        if (e instanceof MonitorException) {
            MonitorException monitorException = (MonitorException) e;
            return ResultUtils.error(monitorException.getCode(), monitorException.getMessage());
        } else {
            logger.error(e);
            return ResultUtils.error(100, "未知错误");
        }
    }
}

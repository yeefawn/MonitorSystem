package com.yeewenfag.exception;

import com.yeewenfag.utils.ResultEnum;

public class MonitorException extends RuntimeException {

    private Integer code;

    public MonitorException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MonitorException(ResultEnum message) {
        super(message.getMessage());
        this.code = message.getCode();
    }

    public Integer getCode() {
        return code;
    }


}

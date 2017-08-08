package com.yeewenfag.utils;

import com.yeewenfag.domain.vo.Result;

public class ResultUtils {

    public static Result error(Integer code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result success(Integer code, String message, T date) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setDate(date);
        return result;
    }

    public static Result error(ResultEnum message){
        Result result = new Result();
        result.setCode(message.getCode());
        result.setMessage(message.getMessage());
        return result;
    }

    public static <T> Result success(ResultEnum message, T date) {
        Result result = new Result();
        result.setCode(message.getCode());
        result.setMessage(message.getMessage());
        result.setDate(date);
        return result;
    }
}

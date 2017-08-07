package com.yeewenfag.utils;

import com.yeewenfag.domain.vo.Result;

public class ResultUtils {

    public static Result error(Integer code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}

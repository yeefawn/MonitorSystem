package com.yeewenfag.domain.vo;

import java.io.Serializable;

public class Result<T> implements Serializable {
    // 结果代码
    private Integer code;

    // 返回信息
    private String message;

    // 数据
    private T date;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}

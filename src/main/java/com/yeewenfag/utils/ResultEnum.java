package com.yeewenfag.utils;

public enum ResultEnum {
    RESULT_SUCCESS(1,"成功"),
    RESULT_FAIL(0, "操作失败"),
    ADD_DATA_NULL(101, "提交数据为空"),
    REQUIRE_NULL(102, "必要数据为空"),
    UNKONW_ERROR(100, "未知错误"),
    DATA_NOT_FOUND(103, "数据库查找不到数据"),
    INSERT_FAIL(104, "数据新增失败"),
    FORMAT_ERROR(105, "数据格式错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

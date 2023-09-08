package com.zgyd.project.common;

/**
 * @author ljy
 * @date 2022/12/16 10:19
 */
public enum ErrorCode {
    SUSSES(0, "请求成功"),
    PARAM_ERROR(401, "请求参数错误");

    private int code;

    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

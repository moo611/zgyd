package com.zgyd.project.common;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

/**
 * @author ljy
 * @date 2022/12/16 10:19
 */
@Builder
//@ApiModel("Response")
public class Response<T> {
    //@ApiModelProperty(value = "成功")
    @Builder.Default
    private Boolean success = true;

    //@ApiModelProperty(value = "返回数据")
    private T data;

    //@ApiModelProperty(value = "编码")
    @Builder.Default
    private Integer code=200;

    //@ApiModelProperty(value = "提示信息")
    private String msg;

    public static Response success() {
        return success(null);
    }

    public static Response success(Object data) {
        return new Response(true, data);
    }

    public static Response error(ErrorCode errorCode) {
        return error(errorCode, null);
    }

    public static Response error(ErrorCode errorCode, Object data) {
        return error(data, errorCode.getCode(), errorCode.getMessage());
    }

    public static Response error(int code, String message) {
        return error(null, code, message);
    }

    public static Response error(Object data, int code, String message) {
        return new Response(false, data, code, message);
    }

    public Response() {
    }

    public Response(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Response(Boolean success, T data,Integer code) {
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public Response(Boolean success, int code, String msg) {
        this.success = success;
        this.data = null;
        this.code = code;
        this.msg = msg;
    }

    public Response(Boolean success, T data, int code, String msg) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.rrrent.theia.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接口返回封装对象,0响应正确
 * @author liujf05
 * @date 2020/3/11
 */
@ApiModel(value = "返回类")
public class Resp<T> {

    public static final int OK_CODE = 200;
    public static final int ERROR_CODE = 0;
    public static final int NEED_LOGIN_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;

    @ApiModelProperty(value = "业务响应码")
    private int code;
    @ApiModelProperty(value = "业务返回数据")
    private T result;
    @ApiModelProperty(value = "异常消息")
    private String message;


    public Resp(int code, T obj, String message) {
        this.code = code;
        this.result = obj;
        this.message = message;
    }

    public Resp(int code, T obj) {
        this.code = code;
        this.result = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}

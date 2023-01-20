package com.xkyii.spry.common.dto;

import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.common.error.ErrorCode;
import com.xkyii.spry.common.error.IErrorCode;
import com.xkyii.spry.common.util.Strings;

public class Response<T> {

    private Integer code;

    private String msg;

    private T data;

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> ok() {
        return build(null, ErrorCode.SUCCESS);
    }

    public static <T> Response<T> ok(T data) {
        return build(data, ErrorCode.SUCCESS);
    }

    public static <T> Response<T> fail() {
        return build(null, ErrorCode.FAIL);
    }

    public static <T> Response<T> fail(T data) {
        return build(data, ErrorCode.FAIL);
    }

    public static <T> Response<T> fail(IErrorCode code) {
        return build(null, code);
    }

    public static <T> Response<T> fail(IErrorCode code, Object... args) {
        return build(null, code, args);
    }

    public static <T> Response<T> fail(ApiException exception) {
        return build(exception.getErrorCode().code(), exception.getMessage());
    }

    public static <T> Response<T> build(T data, IErrorCode code, Object... args) {
        return new Response<>(code.code(), Strings.format(code.message(), args), data);
    }

    public static <T> Response<T> build(Integer code, String msg) {
        return new Response<>(code, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

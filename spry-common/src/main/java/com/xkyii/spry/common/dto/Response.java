package com.xkyii.spry.common.dto;

import com.xkyii.spry.common.constant.ErrorCode;
import com.xkyii.spry.common.error.ApiException;

public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    public Response(Integer code) {
        this(code, null, null);
    }

    public Response(Integer code, String message) {
        this(code, message, null);
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> ok() {
        return new Response<>(ErrorCode.成功);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(ErrorCode.成功, null, data);
    }


    public static <T> Response<T> fail() {
        return new Response<>(ErrorCode.操作失败);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(ErrorCode.操作失败, null, data);
    }

    public static <T> Response<T> fail(Integer code) {
        return new Response<>(code, null, null);
    }

    public static <T> Response<T> fail(ApiException exception) {
        return new Response<>(exception.getCode(), exception.getMessage());
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

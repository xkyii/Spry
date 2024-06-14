package com.xkyss.quarkus.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xkyss.quarkus.rest.error.ErrorCode;

import java.util.Objects;

@SuppressWarnings("ALL")
public class Response<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 分页
     */
    private Page page;

    public Response(Integer code) {
        this(code, null, null, null);
    }

    public Response(Integer code, String message) {
        this(code, message, null, null);
    }

    public Response(Integer code, String message, T data) {
        this(code, message, data, null);
    }

    public Response(Integer code, String message, T data, Page page) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = page;
    }

    public static <T> Response<T> success() {
        return new Response<>(ErrorCode.成功, null, null, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(ErrorCode.成功, null, data, null);
    }

    public static <T> Response<T> success(T data, Page page) {
        return new Response<>(ErrorCode.成功, null, data, page);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(ErrorCode.成功, message, data, null);
    }

    public static <T> Response<T> success(String message, T data, Page page) {
        return new Response<>(ErrorCode.成功, message, data, page);
    }


    public static <T> Response<T> warn() {
        return new Response<>(ErrorCode.警告, null, null, null);
    }

    public static <T> Response<T> warn(T data) {
        return new Response<>(ErrorCode.警告, null, data, null);
    }

    public static <T> Response<T> warn(T data, Page page) {
        return new Response<>(ErrorCode.警告, null, data, page);
    }

    public static <T> Response<T> warn(String message, T data) {
        return new Response<>(ErrorCode.警告, message, data, null);
    }

    public static <T> Response<T> warn(String message, T data, Page page) {
        return new Response<>(ErrorCode.警告, message, data, page);
    }


    public static <T> Response<T> error() {
        return new Response<>(ErrorCode.错误, null, null, null);
    }

    public static <T> Response<T> error(T data) {
        return new Response<>(ErrorCode.错误, null, data, null);
    }

    public static <T> Response<T> error(T data, Page page) {
        return new Response<>(ErrorCode.错误, null, data, page);
    }

    public static <T> Response<T> error(String message, T data) {
        return new Response<>(ErrorCode.错误, message, data, null);
    }

    public static <T> Response<T> error(String message, T data, Page page) {
        return new Response<>(ErrorCode.错误, message, data, page);
    }

    public static <T> Response<T> exception() {
        return new Response<>(ErrorCode.异常, null, null, null);
    }

    public static <T> Response<T> exception(T data) {
        return new Response<>(ErrorCode.异常, null, data, null);
    }

    public static <T> Response<T> exception(T data, Page page) {
        return new Response<>(ErrorCode.异常, null, data, page);
    }

    public static <T> Response<T> exception(String message, T data) {
        return new Response<>(ErrorCode.异常, message, data, null);
    }

    public static <T> Response<T> exception(String message, T data, Page page) {
        return new Response<>(ErrorCode.异常, message, data, page);
    }


    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(ErrorCode.成功, code);
    }

    @JsonIgnore
    public boolean isWarn() {
        return Objects.equals(ErrorCode.警告, code);
    }

    @JsonIgnore
    public boolean isError() {
        return Objects.equals(ErrorCode.错误, code);
    }

    @JsonIgnore
    public boolean isException() {
        return Objects.equals(ErrorCode.异常, code);
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}

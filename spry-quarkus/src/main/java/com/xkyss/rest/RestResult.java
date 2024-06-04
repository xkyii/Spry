package com.xkyss.rest;

import io.vertx.core.json.JsonObject;
import java.util.Objects;

import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * 统一接口返回结果
 */
public class RestResult {

    /**
     * 警告消息码
     */
    public static final int WARN = 601;

    /**
     * TAG: 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * TAG: 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * TAG: 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 实际数据
     */
    private JsonObject jo = new JsonObject();

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public RestResult(int code, String msg) {
        jo.put(CODE_TAG, code);
        jo.put(MSG_TAG, msg);
    }

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public RestResult(int code, String msg, Object data) {
        jo.put(CODE_TAG, code);
        jo.put(MSG_TAG, msg);
        jo.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RestResult success()
    {
        return RestResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static RestResult success(Object data)
    {
        return RestResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static RestResult success(String msg)
    {
        return RestResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RestResult success(String msg, Object data)
    {
        return new RestResult(OK.getStatusCode(), msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RestResult warn(String msg)
    {
        return RestResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RestResult warn(String msg, Object data)
    {
        return new RestResult(WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RestResult error()
    {
        return RestResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static RestResult error(String msg)
    {
        return RestResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static RestResult error(String msg, Object data)
    {
        return new RestResult(INTERNAL_SERVER_ERROR.getStatusCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static RestResult error(int code, String msg)
    {
        return new RestResult(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess()
    {
        return Objects.equals(OK.getStatusCode(), jo.getInteger(CODE_TAG));
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn()
    {
        return Objects.equals(WARN, jo.getInteger(CODE_TAG));
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError()
    {
        return Objects.equals(INTERNAL_SERVER_ERROR.getStatusCode(), jo.getInteger(CODE_TAG));
    }
}

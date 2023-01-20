package com.xkyii.spry.common.error;

/**
 * 错误号
 *
 * 约定:
 *      [0, 10000): 系统内置错误号
 *
 * @author xkyii
 */
public enum ErrorCode implements IErrorCode {
    SUCCESS(0, "操作成功"),
    FAIL(99, "操作失败"),
    UNKNOWN_ERROR(9999, "未知错误");

    private final int code;
    private final String msg;
    ;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.msg;
    }
}

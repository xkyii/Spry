package com.xkyii.spry.admin.constant;

import com.xkyii.spry.common.error.IErrorCode;

/**
 * 错误号
 * 约定范围:  [10000, 20000)
 */
public enum AdminError implements IErrorCode {
    USERNAME_DUPLICATED(0, "用户名{}已经被注册"),
    ;

    private static final int BASE = 10000;

    private final int code;
    private final String msg;
    ;

    AdminError(int code, String msg) {
        this.code = BASE + code;
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
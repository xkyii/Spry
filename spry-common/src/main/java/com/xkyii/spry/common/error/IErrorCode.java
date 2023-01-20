package com.xkyii.spry.common.error;

public interface IErrorCode {

    /**
     * 返回错误码名称
     * @return 枚举名称
     */
    String name();

    /**
     * 返回错误码
     * @return 错误码
     */
    int code();

    /**
     * 返回具体的详细错误描述
     * @return 错误描述
     */
    String message();

    default String detail() {
        return String.format("%s(%d)", message(), code());
    }
}

package com.xkyss.quarkus.rest.error;

@SuppressWarnings("ALL")
public interface ErrorCode {
    Integer 成功 = 0;
    Integer 错误 = 100;
    Integer 警告 = 101;
    Integer 异常 = 102;
    Integer 参数校验失败 = 103;
    Integer 未知错误 = 199;
}


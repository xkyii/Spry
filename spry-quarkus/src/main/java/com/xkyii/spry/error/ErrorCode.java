package com.xkyii.spry.error;

public interface ErrorCode extends com.xkyss.quarkus.rest.error.ErrorCode {
    Integer START = 10000;

    Integer 用户不存在 = START + 100;

    Integer 部门不存在 = START + 200;

    Integer 角色不存在 = START + 300;
}

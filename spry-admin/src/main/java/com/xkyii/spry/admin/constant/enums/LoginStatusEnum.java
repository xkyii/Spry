package com.xkyii.spry.admin.constant.enums;


import com.xkyii.spry.common.constant.BasicEnum;

/**
 * 用户状态
 */
public enum LoginStatusEnum implements BasicEnum<Integer> {
    /**
     * status of user
     */
    LOGIN_SUCCESS(1, "登录成功"),
    LOGOUT(2, "退出成功"),
    REGISTER(3, "注册"),
    LOGIN_FAIL(0, "登录失败");

    private final int value;
    private final String msg;

    LoginStatusEnum(int status, String msg) {
        this.value = status;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return msg;
    }
}

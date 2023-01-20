package com.xkyii.spry.admin.constant;


import com.xkyii.spry.common.constant.ErrorCode;

/**
 * 错误号
 * 约定范围:  [10000, 20000)
 */
public interface AdminError extends ErrorCode {
    // ------------------
    // User
    // ------------------

    Integer 用户名已经被注册 = 10000;

    // ------------------
    // Validate
    // ------------------

    Integer 校验用户名不能为空 = 11001;
    Integer 校验用户名长度 = 11002;
    Integer 校验用户密码不能为空 = 110003;
    Integer 校验用户密码长度 = 11004;

}
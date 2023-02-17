package com.xkyii.spry.admin.constant;


import com.xkyii.spry.common.constant.ErrorCode;

/**
 * 错误号
 * 约定范围:  [10000, 20000)
 */
public interface AdminError extends ErrorCode {

    // ------------------
    // User
    //  From: 10000
    // ------------------

    Integer 用户名已经被注册 = 10000;
    Integer 用户不存在 = 10001;
    Integer 加密失败 = 10002;
    Integer 解密失败 = 10003;
    Integer 密码错误 = 10004;
    Integer 用户未登录 = 10005;

}
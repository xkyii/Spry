package com.xkyii.spry.web.constant;


import com.xkyss.quarkus.server.error.ErrorCode;

/**
 * 错误号
 * 约定范围:  [10000, 20000)
 */
public interface AdminError extends ErrorCode {

    // ------------------
    // User
    //  From: 10000
    // ------------------

    Integer 登录成功 = 10000;
    Integer 登录失败 = 10001;
    Integer 用户不存在 = 10002;
    Integer 加密失败 = 10003;
    Integer 解密失败 = 10004;
    Integer 密码错误 = 10005;
    Integer 用户未登录 = 10006;
    Integer 用户名已经被注册 = 10007;

}

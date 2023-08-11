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

    int 登录成功 = 10000;
    int 登录失败 = 10001;
    int 用户不存在 = 10002;
    int 加密失败 = 10003;
    int 解密失败 = 10004;
    int 密码错误 = 10005;
    int 用户未登录 = 10006;
    int 用户名已经被注册 = 10007;

    int 缓存LoginUser已失效 = 10010;

    int 测试错误 = 19999;
}

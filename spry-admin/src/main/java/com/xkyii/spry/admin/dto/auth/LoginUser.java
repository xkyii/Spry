package com.xkyii.spry.admin.dto.auth;

import com.xkyii.spry.admin.entity.SysUser;


/**
 * 当前登录用户
 */
public class LoginUser {
    private Long userId;
    private String username;
    private Long deptId;
    private Long roleId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录信息
     */
//    private LoginInfo loginInfo = new LoginInfo();

    /**
     * 角色信息
     */
//    private RoleInfo roleInfo = new RoleInfo();

    /**
     * 用户信息
     */
    private SysUser entity;
}

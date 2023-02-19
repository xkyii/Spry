package com.xkyii.spry.admin.dto.auth;

import com.xkyii.spry.admin.entity.SysUser;

/**
 * 当前登录用户
 */
public class LoginUser {

    public LoginUser(SysUser entity, RoleInfo roleInfo) {
        setUsername(entity.getUsername());
        setUserId(entity.getUserId());
        setDeptId(entity.getDeptId());
        setRoleId(entity.getRoleId());
        if (roleInfo != null) {
            this.roleInfo = roleInfo;
        }
        this.entity = entity;
    }

    // --- BaseUser

    private Long userId;
    private String username;
    private Long deptId;
    private Long roleId;

    // ---

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
    private LoginInfo loginInfo = new LoginInfo();

    /**
     * 角色信息
     */
    private RoleInfo roleInfo = new RoleInfo();

    /**
     * 用户信息
     */
    private SysUser entity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public SysUser getEntity() {
        return entity;
    }

    public void setEntity(SysUser entity) {
        this.entity = entity;
    }
}

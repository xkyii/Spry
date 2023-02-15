package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 登录日志
 */
@Entity
@Table(name = "sys_login_info")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysLoginInfo {

    /** 登录日志ID */
    @Id
    @GeneratedValue
    @Column(name = "info_id")
    private java.lang.Long infoId;

    /** 用户账号 */
    @Column(name = "username")
    private java.lang.String username;

    /** 登录IP地址 */
    @Column(name = "ip_address")
    private java.lang.String ipAddress;

    /** 登录地点 */
    @Column(name = "login_location")
    private java.lang.String loginLocation;

    /** 浏览器类型 */
    @Column(name = "browser")
    private java.lang.String browser;

    /** 操作系统 */
    @Column(name = "operation_system")
    private java.lang.String operationSystem;

    /** 登录状态（1成功 0失败） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 提示消息 */
    @Column(name = "msg")
    private java.lang.String msg;

    /** 访问时间 */
    @Column(name = "login_time")
    private java.util.Date loginTime;

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getInfoId() {
        return infoId;
    }

    public void setInfoId(java.lang.Long infoId) {
        this.infoId = infoId;
    }
    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }
    public java.lang.String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(java.lang.String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public java.lang.String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(java.lang.String loginLocation) {
        this.loginLocation = loginLocation;
    }
    public java.lang.String getBrowser() {
        return browser;
    }

    public void setBrowser(java.lang.String browser) {
        this.browser = browser;
    }
    public java.lang.String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(java.lang.String operationSystem) {
        this.operationSystem = operationSystem;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
    }
    public java.lang.String getMsg() {
        return msg;
    }

    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }
    public java.util.Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(java.util.Date loginTime) {
        this.loginTime = loginTime;
    }
    public java.lang.Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Byte deleted) {
        this.deleted = deleted;
    }
}

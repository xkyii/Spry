package com.xkyii.spry.web.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sys_logininfor")
@SuppressWarnings("JpaDataSourceORMInspection")
public class SysLoginInfo extends BaseEntity {

    /** ID */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    /** 用户账号 */
    @Column(name = "user_name")
    private String userName;

    /** 登录状态 0成功 1失败 */
    @Column(name = "status")
    private String status;

    /** 登录IP地址 */
    @Column(name = "ipaddr")
    private String ipaddr;

    /** 登录地点 */
    @Column(name = "login_location")
    private String loginLocation;

    /** 浏览器类型 */
    @Column(name = "browser")
    private String browser;

    /** 操作系统 */
    @Column(name = "os")
    private String os;

    /** 提示消息 */
    @Column(name = "msg")
    private String msg;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "login_time")
    private Date loginTime;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}

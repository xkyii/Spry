package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 用户
 */
@Entity
@Table(name = "sys_user")
@SuppressWarnings({"unused"})
public class SysUser {

    /** 用户ID */
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private java.lang.Long userId;

    /** 职位ID */
    @Column(name = "post_id")
    private java.lang.Long postId;

    /** 角色ID */
    @Column(name = "role_id")
    private java.lang.Long roleId;

    /** 部门ID */
    @Column(name = "dept_id")
    private java.lang.Long deptId;

    /** 用户账号 */
    @Column(name = "username")
    private java.lang.String username;

    /** 用户昵称 */
    @Column(name = "nick_name")
    private java.lang.String nickName;

    /** 用户类型（00系统用户） */
    @Column(name = "user_type")
    private java.lang.Short userType;

    /** 用户邮箱 */
    @Column(name = "email")
    private java.lang.String email;

    /** 手机号码 */
    @Column(name = "phone_number")
    private java.lang.String phoneNumber;

    /** 用户性别（0男 1女 2未知） */
    @Column(name = "sex")
    private java.lang.Short sex;

    /** 头像地址 */
    @Column(name = "avatar")
    private java.lang.String avatar;

    /** 密码 */
    @Column(name = "password")
    private java.lang.String password;

    /** 帐号状态（1正常 2停用 3冻结） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 最后登录IP */
    @Column(name = "login_ip")
    private java.lang.String loginIp;

    /** 最后登录时间 */
    @Column(name = "login_date")
    private java.util.Date loginDate;

    /** 更新者ID */
    @Column(name = "creator_id")
    private java.lang.Long creatorId;

    /** 创建时间 */
    @Column(name = "create_time")
    private java.util.Date createTime;

    /** 更新者ID */
    @Column(name = "updater_id")
    private java.lang.Long updaterId;

    /** 更新时间 */
    @Column(name = "update_time")
    private java.util.Date updateTime;

    /** 备注 */
    @Column(name = "remark")
    private java.lang.String remark;

    /** 删除标志（0代表存在 1代表删除） */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }
    public java.lang.Long getPostId() {
        return postId;
    }

    public void setPostId(java.lang.Long postId) {
        this.postId = postId;
    }
    public java.lang.Long getRoleId() {
        return roleId;
    }

    public void setRoleId(java.lang.Long roleId) {
        this.roleId = roleId;
    }
    public java.lang.Long getDeptId() {
        return deptId;
    }

    public void setDeptId(java.lang.Long deptId) {
        this.deptId = deptId;
    }
    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }
    public java.lang.String getNickName() {
        return nickName;
    }

    public void setNickName(java.lang.String nickName) {
        this.nickName = nickName;
    }
    public java.lang.Short getUserType() {
        return userType;
    }

    public void setUserType(java.lang.Short userType) {
        this.userType = userType;
    }
    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public java.lang.Short getSex() {
        return sex;
    }

    public void setSex(java.lang.Short sex) {
        this.sex = sex;
    }
    public java.lang.String getAvatar() {
        return avatar;
    }

    public void setAvatar(java.lang.String avatar) {
        this.avatar = avatar;
    }
    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
    }
    public java.lang.String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(java.lang.String loginIp) {
        this.loginIp = loginIp;
    }
    public java.util.Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(java.util.Date loginDate) {
        this.loginDate = loginDate;
    }
    public java.lang.Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(java.lang.Long creatorId) {
        this.creatorId = creatorId;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public java.lang.Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(java.lang.Long updaterId) {
        this.updaterId = updaterId;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    public java.lang.Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Byte deleted) {
        this.deleted = deleted;
    }
}

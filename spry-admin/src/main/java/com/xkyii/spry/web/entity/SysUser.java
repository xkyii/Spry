package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sys_user")
@SuppressWarnings("JpaDataSourceORMInspection")
public class SysUser extends BaseEntity {

    /** 用户ID */
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    /** 部门ID */
    @Column(name = "dept_id")
    private Long deptId;

    /** 用户账号 */
    @Column(name = "user_name")
    private String userName;

    /** 用户昵称 */
    @Column(name = "nick_name")
    private String nickName;

    /** 用户邮箱 */
    @Column(name = "email")
    private String email;

    /** 手机号码 */
    @Column(name = "phonenumber")
    private String phonenumber;

    /** 用户性别 */
    @Column(name = "sex")
    private String sex;

    /** 用户头像 */
    @Column(name = "avatar")
    private String avatar;

    /** 密码 */
    @Column(name = "password")
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Column(name = "status")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @Column(name = "del_flag")
    private String delFlag;

    /** 最后登录IP */
    @Column(name = "login_ip")
    private String loginIp;

    /** 最后登录时间 */
    @Column(name = "login_date")
    private Date loginDate;

    /** 部门对象 */
    @Transient
    private SysDept dept;

    /** 角色对象 */
    @Transient
    private List<SysRole> roles;

    /** 角色组 */
    @Transient
    private Long[] roleIds;

    /** 岗位组 */
    @Transient
    private Long[] postIds;

    /** 角色ID */
    @Transient
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}

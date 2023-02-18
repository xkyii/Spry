package com.xkyii.spry.admin.dto.user.get_user_info;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

public class UserDto {
    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "职位ID")
    private Long postId;

    @Schema(title = "角色ID")
    private Long roleId;

    @Schema(title = "部门ID")
    private Long deptId;

    @Schema(title = "部门名称")
    private String deptName;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "用户昵称")
    private String nickName;

    @Schema(title = "用户类型")
    private Integer userType;

    @Schema(title = "邮件")
    private String email;

    @Schema(title = "号码")
    private String phoneNumber;

    @Schema(title = "性别")
    private String sex;

    @Schema(title = "用户头像")
    private String avatar;

    @Schema(title = "状态")
    private String status;

    @Schema(title = "IP")
    private String loginIp;

    @Schema(title = "登录时间")
    private Date loginDate;

    @Schema(title = "创建者ID")
    private Long creatorId;

    @Schema(title = "创建者")
    private String creatorName;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "修改者ID")
    private Long updaterId;

    @Schema(title = "修改者")
    private String updaterName;

    @Schema(title = "修改时间")
    private Date updateTime;

    @Schema(title = "备注")
    private String remark;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

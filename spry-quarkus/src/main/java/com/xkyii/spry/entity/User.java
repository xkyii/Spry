package com.xkyii.spry.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
@Table(name = "t_user")
@Comment("用户")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键")
    private Long id;

    @Column(name = "dept_id")
    @Comment("部门主键")
    private Long deptId;

    @Comment("用户名")
    @Column(name = "username", length = 32, unique = true, nullable = false)
    private String username;

    @Comment("昵称")
    @Column(name = "nickname", length = 32, unique = true)
    private String nickname;

    @Comment("密码")
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Comment("邮箱")
    @Column(name = "email", length = 64)
    private String email;

    @Comment("手机号码")
    @Column(name = "phone", length = 16, unique = true)
    private String phone;

    @Comment("创建人")
    @Column(name = "created_by")
    private Long createdBy;

    @Comment("创建时间")
    @Column(name = "created_at")
    private Date createdAt;

    @Comment("更新人")
    @Column(name = "updated_by")
    private Long updatedBy;

    @Comment("更新时间")
    @Column(name = "updated_at")
    private Date updatedAt;

    @Comment("更新人")
    @Column(name = "deleted_by")
    private Long deletedBy;

    @Comment("更新时间")
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Comment("状态")
    @Column(name = "status")
    private Integer status = 0;

    @Comment("备注")
    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

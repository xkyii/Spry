package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 部门
 */
@Entity
@Table(name = "sys_dept")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysDept {

    /** 部门ID */
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private java.lang.Long deptId;

    /** 父部门ID */
    @Column(name = "parent_id")
    private java.lang.Long parentId;

    /** 祖级列表 */
    @Column(name = "ancestors")
    private java.lang.String ancestors;

    /** 部门名称 */
    @Column(name = "dept_name")
    private java.lang.String deptName;

    /** 显示顺序 */
    @Column(name = "order_num")
    private java.lang.Integer orderNum;

    /** 负责人主键 */
    @Column(name = "leader_id")
    private java.lang.Long leaderId;

    /** 负责人 */
    @Column(name = "leader_name")
    private java.lang.String leaderName;

    /** 联系电话 */
    @Column(name = "phone")
    private java.lang.String phone;

    /** 邮箱 */
    @Column(name = "email")
    private java.lang.String email;

    /** 部门状态（0正常 1停用） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 创建者ID */
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

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getDeptId() {
        return deptId;
    }

    public void setDeptId(java.lang.Long deptId) {
        this.deptId = deptId;
    }
    public java.lang.Long getParentId() {
        return parentId;
    }

    public void setParentId(java.lang.Long parentId) {
        this.parentId = parentId;
    }
    public java.lang.String getAncestors() {
        return ancestors;
    }

    public void setAncestors(java.lang.String ancestors) {
        this.ancestors = ancestors;
    }
    public java.lang.String getDeptName() {
        return deptName;
    }

    public void setDeptName(java.lang.String deptName) {
        this.deptName = deptName;
    }
    public java.lang.Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.lang.Integer orderNum) {
        this.orderNum = orderNum;
    }
    public java.lang.Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(java.lang.Long leaderId) {
        this.leaderId = leaderId;
    }
    public java.lang.String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(java.lang.String leaderName) {
        this.leaderName = leaderName;
    }
    public java.lang.String getPhone() {
        return phone;
    }

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
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
    public java.lang.Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Byte deleted) {
        this.deleted = deleted;
    }
}

package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 角色
 */
@Entity
@Table(name = "sys_role")
@NamedQuery(name = "SysRole.getRoleOfUser", query = "" +
    "SELECT r FROM SysRole as r " +
    " LEFT JOIN SysUser as u ON r.deleted=0 AND u.userId=?1")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused", "SqlDialectInspection", "SqlNoDataSourceInspection"})
public class SysRole {

    /** 角色ID */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private java.lang.Long roleId;

    /** 角色名称 */
    @Column(name = "role_name")
    private java.lang.String roleName;

    /** 角色权限字符串 */
    @Column(name = "role_key")
    private java.lang.String roleKey;

    /** 显示顺序 */
    @Column(name = "role_sort")
    private java.lang.Integer roleSort;

    /** 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限） */
    @Column(name = "data_scope")
    private java.lang.Short dataScope;

    /** 角色所拥有的部门数据权限 */
    @Column(name = "dept_id_set")
    private java.lang.String deptIdSet;

    /** 角色状态（1正常 0停用） */
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

    /** 备注 */
    @Column(name = "remark")
    private java.lang.String remark;

    /** 删除标志（0代表存在 1代表删除） */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getRoleId() {
        return roleId;
    }

    public void setRoleId(java.lang.Long roleId) {
        this.roleId = roleId;
    }
    public java.lang.String getRoleName() {
        return roleName;
    }

    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }
    public java.lang.String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(java.lang.String roleKey) {
        this.roleKey = roleKey;
    }
    public java.lang.Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(java.lang.Integer roleSort) {
        this.roleSort = roleSort;
    }
    public java.lang.Short getDataScope() {
        return dataScope;
    }

    public void setDataScope(java.lang.Short dataScope) {
        this.dataScope = dataScope;
    }
    public java.lang.String getDeptIdSet() {
        return deptIdSet;
    }

    public void setDeptIdSet(java.lang.String deptIdSet) {
        this.deptIdSet = deptIdSet;
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

package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(SysUserRole.class)
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {

    /** 用户ID */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /** 角色ID */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}

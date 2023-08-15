package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserRole that = (SysUserRole) o;

        if (!Objects.equals(userId, that.userId)) return false;
        return Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}

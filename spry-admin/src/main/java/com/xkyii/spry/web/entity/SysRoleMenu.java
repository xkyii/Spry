package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(SysRoleMenu.class)
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    /** 角色ID */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /** 菜单ID */
    @Id
    @Column(name = "menu_id")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRoleMenu that = (SysRoleMenu) o;

        if (!Objects.equals(roleId, that.roleId)) return false;
        return Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        return result;
    }
}

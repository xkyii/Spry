package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 角色菜单关联
 */
@Entity
@Table(name = "sys_role_menu")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysRoleMenu implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private java.lang.Long roleId;

    /** 菜单ID */
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private java.lang.Long menuId;


    public java.lang.Long getRoleId() {
        return roleId;
    }

    public void setRoleId(java.lang.Long roleId) {
        this.roleId = roleId;
    }
    public java.lang.Long getMenuId() {
        return menuId;
    }

    public void setMenuId(java.lang.Long menuId) {
        this.menuId = menuId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        };
        if (o == null || getClass() != o.getClass()) {
            return false;
        };

        SysRoleMenu that = (SysRoleMenu) o;

        if (!this.roleId.equals(that.roleId)) {
            return false;
        }
        if (!this.menuId.equals(that.menuId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + roleId.hashCode();
        result = 31 * result + menuId.hashCode();
        return result;
    }
}

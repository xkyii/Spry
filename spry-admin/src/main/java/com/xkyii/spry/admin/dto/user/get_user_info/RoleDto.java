package com.xkyii.spry.admin.dto.user.get_user_info;

import java.util.Set;

public class RoleDto {

    private Long roleId;
    private String roleName;
//    private DataScopeEnum dataScope;
    private Set<Long> deptIdSet;
    private String roleKey;
    private Set<String> menuPermissions;
    private Set<Long> menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Long> getDeptIdSet() {
        return deptIdSet;
    }

    public void setDeptIdSet(Set<Long> deptIdSet) {
        this.deptIdSet = deptIdSet;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Set<String> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Set<String> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }

    public Set<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Set<Long> menuIds) {
        this.menuIds = menuIds;
    }
}

package com.xkyii.spry.admin.dto.auth;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.xkyii.spry.admin.entity.SysRole;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleInfo {

    public RoleInfo() {

    }

    public RoleInfo(SysRole entity, String roleKey, Set<String> menuPermissions, Set<Long> menuIds) {
        if (entity != null) {
            this.roleId = entity.getRoleId();
            this.roleName = entity.getRoleName();
//            this.dataScope = BasicEnumUtil.fromValue(DataScopeEnum.class, entity.getDataScope());

            if(StrUtil.isNotEmpty(entity.getDeptIdSet())) {
                this.deptIdSet = StrUtil.split(entity.getDeptIdSet(), ",").stream()
                    .map(Convert::toLong).collect(Collectors.toSet());
            }

            this.roleKey = roleKey;
            this.menuPermissions = menuPermissions != null ? menuPermissions : new HashSet<>();
            this.menuIds = menuIds != null ? menuIds : new HashSet<>();

        }
    }

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

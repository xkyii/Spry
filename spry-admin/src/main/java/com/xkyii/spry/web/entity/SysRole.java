package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "sys_role")
@SuppressWarnings("JpaDataSourceORMInspection")
public class SysRole extends BaseEntity {

    /** 角色ID */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;

    /** 角色名称 */
    @Column(name = "role_name")
    private String roleName;

    /** 角色权限 */
    @Column(name = "role_key")
    private String roleKey;

    /** 角色排序 */
    @Column(name = "role_sort")
    private Integer roleSort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限） */
    @Column(name = "data_scope")
    private String dataScope;

    /** 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示） */
    @Column(name = "menu_check_strictly")
    private boolean menuCheckStrictly;

    /** 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ） */
    @Column(name = "dept_check_strictly")
    private boolean deptCheckStrictly;

    /** 角色状态（0正常 1停用） */
    @Column(name = "status")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @Column(name = "del_flag")
    private String delFlag;

    /** 用户是否存在此角色标识 默认不存在 */
    @Column(name = "flag")
    private boolean flag = false;

    /** 菜单组 */
    @Transient
    private Long[] menuIds;

    /** 部门组（数据权限） */
    @Transient
    private Long[] deptIds;

    /** 角色菜单权限 */
    @Transient
    private Set<String> permissions;

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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public boolean isMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(boolean menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public boolean isDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(boolean deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}

package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 菜单
 */
@Entity
@Table(name = "sys_menu")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysMenu {

    /** 菜单ID */
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private java.lang.Long menuId;

    /** 菜单名称 */
    @Column(name = "menu_name")
    private java.lang.String menuName;

    /** 父菜单ID */
    @Column(name = "parent_id")
    private java.lang.Long parentId;

    /** 显示顺序 */
    @Column(name = "order_num")
    private java.lang.Integer orderNum;

    /** 路由地址 */
    @Column(name = "path")
    private java.lang.String path;

    /** 组件路径 */
    @Column(name = "component")
    private java.lang.String component;

    /** 路由参数 */
    @Column(name = "query")
    private java.lang.String query;

    /** 是否为外链（1是 0否） */
    @Column(name = "is_external")
    private java.lang.Byte isExternal;

    /** 是否缓存（1缓存 0不缓存） */
    @Column(name = "is_cache")
    private java.lang.Byte isCache;

    /** 菜单类型（M=1目录 C=2菜单 F=3按钮） */
    @Column(name = "menu_type")
    private java.lang.Short menuType;

    /** 菜单状态（1显示 0隐藏） */
    @Column(name = "is_visible")
    private java.lang.Byte isVisible;

    /** 菜单状态（0正常 1停用） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 权限标识 */
    @Column(name = "perms")
    private java.lang.String perms;

    /** 菜单图标 */
    @Column(name = "icon")
    private java.lang.String icon;

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

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getMenuId() {
        return menuId;
    }

    public void setMenuId(java.lang.Long menuId) {
        this.menuId = menuId;
    }
    public java.lang.String getMenuName() {
        return menuName;
    }

    public void setMenuName(java.lang.String menuName) {
        this.menuName = menuName;
    }
    public java.lang.Long getParentId() {
        return parentId;
    }

    public void setParentId(java.lang.Long parentId) {
        this.parentId = parentId;
    }
    public java.lang.Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.lang.Integer orderNum) {
        this.orderNum = orderNum;
    }
    public java.lang.String getPath() {
        return path;
    }

    public void setPath(java.lang.String path) {
        this.path = path;
    }
    public java.lang.String getComponent() {
        return component;
    }

    public void setComponent(java.lang.String component) {
        this.component = component;
    }
    public java.lang.String getQuery() {
        return query;
    }

    public void setQuery(java.lang.String query) {
        this.query = query;
    }
    public java.lang.Byte getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(java.lang.Byte isExternal) {
        this.isExternal = isExternal;
    }
    public java.lang.Byte getIsCache() {
        return isCache;
    }

    public void setIsCache(java.lang.Byte isCache) {
        this.isCache = isCache;
    }
    public java.lang.Short getMenuType() {
        return menuType;
    }

    public void setMenuType(java.lang.Short menuType) {
        this.menuType = menuType;
    }
    public java.lang.Byte getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(java.lang.Byte isVisible) {
        this.isVisible = isVisible;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
    }
    public java.lang.String getPerms() {
        return perms;
    }

    public void setPerms(java.lang.String perms) {
        this.perms = perms;
    }
    public java.lang.String getIcon() {
        return icon;
    }

    public void setIcon(java.lang.String icon) {
        this.icon = icon;
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

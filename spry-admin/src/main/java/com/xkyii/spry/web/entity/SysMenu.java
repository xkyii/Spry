package com.xkyii.spry.web.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_menu")
@NamedQueries({
    @NamedQuery(name = "SysMenu.selectMenuPermsByUserId",
        query = "select distinct m.perms " +
            "from SysMenu m " +
            " left join SysRoleMenu rm on m.menuId = rm.menuId " +
            " left join SysUserRole ur on rm.roleId = ur.roleId " +
            " left join SysRole  r on r.roleId = ur.roleId " +
            "where m.status = '0' and r.status = '0' and ur.userId = :userId"
    ),
    @NamedQuery(name = "SysMenu.selectMenuPermsByRoleId",
        query = "select distinct m.perms " +
            "from SysMenu m " +
            " left join SysRoleMenu rm on m.menuId = rm.menuId " +
            "where m.status='0' and rm.roleId = :roleId "
    )
})
public class SysMenu {

    /** 菜单ID */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    /** 菜单名称 */
    @Column(name = "menu_name")
    private String menuName;

    /** 父菜单名称 */
    @Transient
    private String parentName;

    /** 父菜单ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 显示顺序 */
    @Column(name = "order_num")
    private Integer orderNum;

    /** 路由地址 */
    @Column(name = "path")
    private String path;

    /** 组件路径 */
    @Column(name = "component")
    private String component;

    /** 路由参数 */
    @Column(name = "query")
    private String query;

    /** 是否为外链（0是 1否） */
    @Column(name = "is_frame")
    private String isFrame;

    /** 是否缓存（0缓存 1不缓存） */
    @Column(name = "is_cache")
    private String isCache;

    /** 类型（M目录 C菜单 F按钮） */
    @Column(name = "menu_type")
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    @Column(name = "visible")
    private String visible;

    /** 菜单状态（0正常 1停用） */
    @Column(name = "status")
    private String status;

    /** 权限字符串 */
    @Column(name = "perms")
    private String perms;

    /** 菜单图标 */
    @Column(name = "icon")
    private String icon;

    /** 子菜单 */
    @Transient
    private List<SysMenu> children = new ArrayList<SysMenu>();

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(String isFrame) {
        this.isFrame = isFrame;
    }

    public String getIsCache() {
        return isCache;
    }

    public void setIsCache(String isCache) {
        this.isCache = isCache;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}

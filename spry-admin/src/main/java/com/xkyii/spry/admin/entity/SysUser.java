package com.xkyii.spry.admin.entity;

import com.xkyii.spry.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SysUser extends BaseEntity {

    /** 用户ID */
    @Id
    public Long userId;

    /** 部门ID */
    public Long deptId;

    /** 部门父ID */
    public Long parentId;

    /** 角色ID */
    public Long roleId;

    /** 登录名称 */
    public String loginName;

    /** 用户名称 */
    public String userName;

    /** 用户类型 */
    public String userType;

    /** 用户邮箱 */
    public String email;

    /** 手机号码 */
    public String phonenumber;

    /** 用户性别 */
    public String sex;

    /** 用户头像 */
    public String avatar;

    /** 密码 */
    public String password;

    /** 盐加密 */
    public String salt;

    /** 帐号状态（0正常 1停用） */
    public String status;

    /** 删除标志（0代表存在 2代表删除） */
    public String delFlag;

    /** 最后登录IP */
    public String loginIp;

    /** 最后登录时间 */
    public Date loginDate;

    /** 密码最后更新时间 */
    public Date pwdUpdateDate;

    /** 部门对象 */
//    public SysDept dept;

//    public List<SysRole> roles;

    /** 角色组 */
//    public Long[] roleIds;

    /** 岗位组 */
//    public Long[] postIds;
}

package com.xkyii.spry.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "t_user_role")
@Comment("用户角色关联")
public class UserRole {

    @Id
    @Column(name = "user_id")
    @Comment("用户主键")
    private Long userId;

    @Id
    @Column(name = "role_id")
    @Comment("角色主键")
    private Long roleId;

    @Comment("备注")
    @Column(name = "remark")
    private String remark;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

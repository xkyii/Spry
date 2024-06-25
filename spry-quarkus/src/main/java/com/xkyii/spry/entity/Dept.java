package com.xkyii.spry.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_dept")
public class Dept {
    @Id
    @Comment("主键")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("名称")
    @Column(name = "name", length = 64)
    private String name;

    @Comment("代码")
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "ancestors", length = 64)
    private String ancestors;

    @Column(name = "leader", length = 32)
    private String leader;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "phone", length = 16)
    private String phone;

    @Comment("创建人")
    @Column(name = "created_by")
    private Long createdBy;

    @Comment("创建时间")
    @Column(name = "created_at")
    private Date createdAt;

    @Comment("更新人")
    @Column(name = "updated_by")
    private Long updatedBy;

    @Comment("更新时间")
    @Column(name = "updated_at")
    private Date updatedAt;

    @Comment("更新人")
    @Column(name = "deleted_by")
    private Long deletedBy;

    @Comment("更新时间")
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Comment("状态")
    @Column(name = "status")
    private Integer status = 0;

    @Comment("备注")
    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

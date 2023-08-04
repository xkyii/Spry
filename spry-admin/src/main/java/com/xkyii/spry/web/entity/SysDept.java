package com.xkyii.spry.web.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Token出参")
@Entity
@Table(name = "sys_dept")
@SuppressWarnings("JpaDataSourceORMInspection")
public class SysDept extends BaseEntity {

    /** 部门ID */
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private Long deptId;

    /** 父部门ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 祖级列表 */
    @Column(name = "ancestors")
    private String ancestors;

    /** 部门名称 */
    @Column(name = "dept_name")
    private String deptName;

    /** 显示顺序 */
    @Column(name = "order_num")
    private Integer orderNum;

    /** 负责人 */
    @Column(name = "leader")
    private String leader;

    /** 联系电话 */
    @Column(name = "phone")
    private String phone;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 部门状态:0正常,1停用 */
    @Column(name = "status")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @JsonIgnore
    @Column(name = "del_flag")
    private String delFlag;

    /** 父部门名称 */
    @Transient
    private String parentName;

    /** 子部门 */
    @Transient
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }
}

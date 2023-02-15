package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 岗位
 */
@Entity
@Table(name = "sys_post")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysPost {

    /** 岗位主键 */
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private java.lang.Long postId;

    /** 岗位编码 */
    @Column(name = "post_code")
    private java.lang.String postCode;

    /** 岗位名称 */
    @Column(name = "post_name")
    private java.lang.String postName;

    /** 显示顺序 */
    @Column(name = "post_sort")
    private java.lang.Integer postSort;

    /** 状态（1正常 0停用） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 备注 */
    @Column(name = "remark")
    private java.lang.String remark;

    /**  */
    @Column(name = "creator_id")
    private java.lang.Long creatorId;

    /** 创建时间 */
    @Column(name = "create_time")
    private java.util.Date createTime;

    /**  */
    @Column(name = "updater_id")
    private java.lang.Long updaterId;

    /** 更新时间 */
    @Column(name = "update_time")
    private java.util.Date updateTime;

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getPostId() {
        return postId;
    }

    public void setPostId(java.lang.Long postId) {
        this.postId = postId;
    }
    public java.lang.String getPostCode() {
        return postCode;
    }

    public void setPostCode(java.lang.String postCode) {
        this.postCode = postCode;
    }
    public java.lang.String getPostName() {
        return postName;
    }

    public void setPostName(java.lang.String postName) {
        this.postName = postName;
    }
    public java.lang.Integer getPostSort() {
        return postSort;
    }

    public void setPostSort(java.lang.Integer postSort) {
        this.postSort = postSort;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
    }
    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
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
    public java.lang.Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Byte deleted) {
        this.deleted = deleted;
    }
}

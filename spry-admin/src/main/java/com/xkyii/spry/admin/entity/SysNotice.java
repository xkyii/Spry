package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 公告
 */
@Entity
@Table(name = "sys_notice")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysNotice {

    /** 公告ID */
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private java.lang.Integer noticeId;

    /** 公告标题 */
    @Column(name = "notice_title")
    private java.lang.String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @Column(name = "notice_type")
    private java.lang.Short noticeType;

    /** 公告内容 */
    @Column(name = "notice_content")
    private java.lang.String noticeContent;

    /** 公告状态（1正常 0关闭） */
    @Column(name = "status")
    private java.lang.Short status;

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


    public java.lang.Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(java.lang.Integer noticeId) {
        this.noticeId = noticeId;
    }
    public java.lang.String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(java.lang.String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
    public java.lang.Short getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(java.lang.Short noticeType) {
        this.noticeType = noticeType;
    }
    public java.lang.String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(java.lang.String noticeContent) {
        this.noticeContent = noticeContent;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
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

package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 参数配置
 */
@Entity
@Table(name = "sys_config")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysConfig {

    /** 参数ID */
    @Id
    @GeneratedValue
    @Column(name = "config_id")
    private java.lang.Integer configId;

    /** 配置名称 */
    @Column(name = "config_name")
    private java.lang.String configName;

    /** 配置键名 */
    @Column(name = "config_key")
    private java.lang.String configKey;

    /** 可选的选项 */
    @Column(name = "config_options")
    private java.lang.String configOptions;

    /** 配置值 */
    @Column(name = "config_value")
    private java.lang.String configValue;

    /** 是否允许修改 */
    @Column(name = "is_allow_change")
    private java.lang.Integer isAllowChange;

    /** 创建者ID */
    @Column(name = "creator_id")
    private java.lang.Integer creatorId;

    /** 更新者ID */
    @Column(name = "updater_id")
    private java.lang.Integer updaterId;

    /** 更新时间 */
    @Column(name = "update_time")
    private java.util.Date updateTime;

    /** 创建时间 */
    @Column(name = "create_time")
    private java.util.Date createTime;

    /** 备注 */
    @Column(name = "remark")
    private java.lang.String remark;

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Integer deleted;


    public java.lang.Integer getConfigId() {
        return configId;
    }

    public void setConfigId(java.lang.Integer configId) {
        this.configId = configId;
    }
    public java.lang.String getConfigName() {
        return configName;
    }

    public void setConfigName(java.lang.String configName) {
        this.configName = configName;
    }
    public java.lang.String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(java.lang.String configKey) {
        this.configKey = configKey;
    }
    public java.lang.String getConfigOptions() {
        return configOptions;
    }

    public void setConfigOptions(java.lang.String configOptions) {
        this.configOptions = configOptions;
    }
    public java.lang.String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(java.lang.String configValue) {
        this.configValue = configValue;
    }
    public java.lang.Integer getIsAllowChange() {
        return isAllowChange;
    }

    public void setIsAllowChange(java.lang.Integer isAllowChange) {
        this.isAllowChange = isAllowChange;
    }
    public java.lang.Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(java.lang.Integer creatorId) {
        this.creatorId = creatorId;
    }
    public java.lang.Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(java.lang.Integer updaterId) {
        this.updaterId = updaterId;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    public java.lang.Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Integer deleted) {
        this.deleted = deleted;
    }
}

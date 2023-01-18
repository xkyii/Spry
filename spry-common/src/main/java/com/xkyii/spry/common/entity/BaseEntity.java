package com.xkyii.spry.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BaseEntity {

    /** 搜索值 */
    public String searchValue;

    /** 创建者 */
    public String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    /** 更新者 */
    public String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updateTime;

    /** 备注 */
    public String remark;
}

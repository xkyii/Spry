package com.xkyii.spry.admin.entity;

import jakarta.persistence.*;

/**
 * 操作日志
 */
@Entity
@Table(name = "sys_operation_log")
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class SysOperationLog {

    /** 操作ID */
    @Id
    @GeneratedValue
    @Column(name = "operation_id")
    private java.lang.Long operationId;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @Column(name = "business_type")
    private java.lang.Short businessType;

    /** 请求方式 */
    @Column(name = "request_method")
    private java.lang.Short requestMethod;

    /** 请求模块 */
    @Column(name = "request_module")
    private java.lang.String requestModule;

    /** 请求URL */
    @Column(name = "request_url")
    private java.lang.String requestUrl;

    /** 调用方法 */
    @Column(name = "called_method")
    private java.lang.String calledMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    @Column(name = "operator_type")
    private java.lang.Short operatorType;

    /** 用户ID */
    @Column(name = "user_id")
    private java.lang.Long userId;

    /** 操作人员 */
    @Column(name = "username")
    private java.lang.String username;

    /** 操作人员IP */
    @Column(name = "operator_ip")
    private java.lang.String operatorIp;

    /** 操作地点 */
    @Column(name = "operator_location")
    private java.lang.String operatorLocation;

    /** 部门ID */
    @Column(name = "dept_id")
    private java.lang.Long deptId;

    /** 部门名称 */
    @Column(name = "dept_name")
    private java.lang.String deptName;

    /** 请求参数 */
    @Column(name = "operation_param")
    private java.lang.String operationParam;

    /** 返回参数 */
    @Column(name = "operation_result")
    private java.lang.String operationResult;

    /** 操作状态（1正常 0异常） */
    @Column(name = "status")
    private java.lang.Short status;

    /** 错误消息 */
    @Column(name = "error_stack")
    private java.lang.String errorStack;

    /** 操作时间 */
    @Column(name = "operation_time")
    private java.util.Date operationTime;

    /** 逻辑删除 */
    @Column(name = "deleted")
    private java.lang.Byte deleted;


    public java.lang.Long getOperationId() {
        return operationId;
    }

    public void setOperationId(java.lang.Long operationId) {
        this.operationId = operationId;
    }
    public java.lang.Short getBusinessType() {
        return businessType;
    }

    public void setBusinessType(java.lang.Short businessType) {
        this.businessType = businessType;
    }
    public java.lang.Short getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(java.lang.Short requestMethod) {
        this.requestMethod = requestMethod;
    }
    public java.lang.String getRequestModule() {
        return requestModule;
    }

    public void setRequestModule(java.lang.String requestModule) {
        this.requestModule = requestModule;
    }
    public java.lang.String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(java.lang.String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public java.lang.String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(java.lang.String calledMethod) {
        this.calledMethod = calledMethod;
    }
    public java.lang.Short getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(java.lang.Short operatorType) {
        this.operatorType = operatorType;
    }
    public java.lang.Long getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }
    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }
    public java.lang.String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(java.lang.String operatorIp) {
        this.operatorIp = operatorIp;
    }
    public java.lang.String getOperatorLocation() {
        return operatorLocation;
    }

    public void setOperatorLocation(java.lang.String operatorLocation) {
        this.operatorLocation = operatorLocation;
    }
    public java.lang.Long getDeptId() {
        return deptId;
    }

    public void setDeptId(java.lang.Long deptId) {
        this.deptId = deptId;
    }
    public java.lang.String getDeptName() {
        return deptName;
    }

    public void setDeptName(java.lang.String deptName) {
        this.deptName = deptName;
    }
    public java.lang.String getOperationParam() {
        return operationParam;
    }

    public void setOperationParam(java.lang.String operationParam) {
        this.operationParam = operationParam;
    }
    public java.lang.String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(java.lang.String operationResult) {
        this.operationResult = operationResult;
    }
    public java.lang.Short getStatus() {
        return status;
    }

    public void setStatus(java.lang.Short status) {
        this.status = status;
    }
    public java.lang.String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(java.lang.String errorStack) {
        this.errorStack = errorStack;
    }
    public java.util.Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(java.util.Date operationTime) {
        this.operationTime = operationTime;
    }
    public java.lang.Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Byte deleted) {
        this.deleted = deleted;
    }
}

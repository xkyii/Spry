package com.xkyss.quarkus.rest.dto;

public class ValidateInfo {
    /**
     * 字段名
     */
    private String field;

    /**
     * 信息
     */
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

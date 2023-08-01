package com.xkyii.spry.common.error;

public class ApiException extends RuntimeException {
    private final Integer code;

    public ApiException(Integer code) {
        this.code = code;
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}


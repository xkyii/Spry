package com.xkyss.quarkus.rest.error;

public class RestException extends RuntimeException {
    private final Integer code;

    public RestException(Integer code) {
        this.code = code;
    }

    public RestException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public RestException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public RestException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
package com.xkyii.spry.common.resource;

import com.xkyii.spry.common.config.SpryConfig;
import com.xkyii.spry.common.constant.ErrorCode;
import com.xkyii.spry.common.dto.Response;

public abstract class AResource {
    protected final SpryConfig config;

    protected AResource(SpryConfig config) {
        this.config = config;
    }

    public <T> Response<T> ok() {
        return new Response<>(ErrorCode.成功, config.getCodeMessage(ErrorCode.成功));
    }

    public <T> Response<T> ok(T data) {
        return new Response<>(ErrorCode.成功, config.getCodeMessage(ErrorCode.成功), data);
    }
}

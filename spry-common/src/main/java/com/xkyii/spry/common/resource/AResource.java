package com.xkyii.spry.common.resource;

import com.xkyii.spry.common.constant.ErrorCode;
import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ErrorMessageManager;

public abstract class AResource {
    protected final ErrorMessageManager emm;

    protected AResource(ErrorMessageManager emm) {
        this.emm = emm;
    }

    public <T> Response<T> ok() {
        return new Response<>(ErrorCode.成功, emm.getMessage(ErrorCode.成功));
    }

    public <T> Response<T> ok(T data) {
        return new Response<>(ErrorCode.成功, emm.getMessage(ErrorCode.成功), data);
    }
}

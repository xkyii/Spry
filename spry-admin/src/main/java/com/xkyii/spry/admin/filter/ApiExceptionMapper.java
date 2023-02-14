package com.xkyii.spry.admin.filter;

import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.common.error.ErrorMessageManager;
import com.xkyss.core.util.Stringx;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * 业务异常统一处理
 */
@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {
    @Inject
    ErrorMessageManager emm;


    @Override
    public Response toResponse(ApiException e) {
        com.xkyii.spry.common.dto.Response<String> r = new com.xkyii.spry.common.dto.Response<>(e.getCode());
        if (StringUtil.isNullOrEmpty(e.getMessage())) {
            r.setMessage(emm.getMessage(r.getCode()));
        }
        else {
            String[] split = e.getMessage().trim().split("\\s*,\\s*");
            r.setMessage(Stringx.arrayFormat(emm.getMessage(r.getCode()), split));
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(r)
                .build();
    }

}

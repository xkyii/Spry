package com.xkyii.spry.admin.filter;

import com.xkyii.spry.common.error.ErrorCode;
import io.vertx.core.json.Json;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        com.xkyii.spry.common.dto.Response<Object> r = com.xkyii.spry.common.dto.Response.fail(ErrorCode.EXCEPTION);
        return Response
            .status(400)
            .entity(Json.encode(r))
            .build();
    }

}

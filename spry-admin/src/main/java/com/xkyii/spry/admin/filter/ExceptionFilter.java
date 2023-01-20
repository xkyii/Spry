package com.xkyii.spry.admin.filter;

import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.common.error.ErrorCode;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Override
    public Response toResponse(Exception exception) {
        logger.error(ErrorCode.UNKNOWN_ERROR.detail(), exception);

        if (exception instanceof ApiException) {
            return toApiExceptionResponse((ApiException) exception);
        }

        return toExceptionResponse(exception);
    }

    private Response toApiExceptionResponse(ApiException exception) {
        com.xkyii.spry.common.dto.Response<Object> r = com.xkyii.spry.common.dto.Response.fail(exception);
        return Response
            .status(400)
            .entity(Json.encode(r))
            .build();
    }

    private Response toExceptionResponse(Exception exception) {
        com.xkyii.spry.common.dto.Response<String> r = com.xkyii.spry.common.dto.Response.fail(ErrorCode.EXCEPTION);
        r.setData(exception.getMessage());
        return Response
            .status(400)
            .entity(Json.encode(r))
            .build();
    }

}

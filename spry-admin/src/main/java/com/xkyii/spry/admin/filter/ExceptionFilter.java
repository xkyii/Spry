package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.common.config.SpryConfig;
import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ApiException;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Inject
    SpryConfig config;

    @Override
    public javax.ws.rs.core.Response toResponse(Exception exception) {
        logger.error(AdminError.操作异常, exception);

        if (exception instanceof ApiException) {
            return toApiExceptionResponse((ApiException) exception);
        }

        return toExceptionResponse(exception);
    }

    private javax.ws.rs.core.Response toApiExceptionResponse(ApiException exception) {
        Response<String> r = new Response<>(exception.getCode(), exception.getMessage());

        return toResponse(r);
    }

    private javax.ws.rs.core.Response toExceptionResponse(Exception exception) {
        Response<String> r = new Response<>(AdminError.操作异常, config.getCodeMessage(AdminError.操作异常));
        r.setData(exception.getMessage());

        return toResponse(r);
    }

    private <T> javax.ws.rs.core.Response toResponse(Response<T> r) {
        return javax.ws.rs.core.Response
            .status(400)
            .entity(Json.encodePrettily(r))
            .build();
    }

}

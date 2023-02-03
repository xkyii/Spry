package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.common.error.ErrorMessageManager;
import com.xkyii.spry.common.util.Strings;
import io.vertx.core.json.Json;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageManager emm;

    @Override
    public jakarta.ws.rs.core.Response toResponse(Exception exception) {
        logger.error(AdminError.操作异常, exception);

        if (exception instanceof ApiException) {
            return toApiExceptionResponse((ApiException) exception);
        }

        return toExceptionResponse(exception);
    }

    private jakarta.ws.rs.core.Response toApiExceptionResponse(ApiException exception) {
        logger.infof("Filter ApiException: %d, %s", exception.getCode(), exception.getMessage());

        Response<String> r = new Response<>(exception.getCode());
        if (exception.getMessage() == null && exception.getMessage().equals("")) {
            r.setMessage(emm.getMessage(r.getCode()));
        }
        else {
            String[] split = exception.getMessage().trim().split("\\s*,\\s*");
            r.setMessage(Strings.arrayFormat(emm.getMessage(r.getCode()), split));
        }

        return toResponse(r);
    }

    private jakarta.ws.rs.core.Response toExceptionResponse(Exception exception) {
        Response<String> r = new Response<>(AdminError.操作异常, emm.getMessage(AdminError.操作异常));
        r.setData(exception.getMessage());

        return toResponse(r);
    }

    private <T> jakarta.ws.rs.core.Response toResponse(Response<T> r) {
        return jakarta.ws.rs.core.Response
            .status(400)
            .entity(Json.encodePrettily(r))
            .build();
    }

}

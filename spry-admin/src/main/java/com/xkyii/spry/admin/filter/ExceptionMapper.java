package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.constant.AdminError;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;


/**
 * 其他异常处理
 */
@Provider
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Override
    public Response toResponse(Exception e) {
        logger.warn("未知错误", e);

        com.xkyii.spry.common.dto.Response<Object> r = new com.xkyii.spry.common.dto.Response<>(AdminError.未知错误);
        r.setMessage(e.toString());
//        r.setData(e);

        return Response.ok(Response.Status.BAD_REQUEST)
                .entity(r)
                .build();
    }
}

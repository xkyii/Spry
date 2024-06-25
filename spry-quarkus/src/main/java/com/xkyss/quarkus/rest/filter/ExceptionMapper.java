package com.xkyss.quarkus.rest.filter;

import com.xkyss.quarkus.rest.service.ErrorMessageService;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常处理
 */
@Provider
@IfBuildProperty(name = "xkyss.build.rest.exception-mapper.enabled", stringValue = "true")
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<Exception> {
    @Inject
    Logger logger;

    @Inject
    ErrorMessageService ems;

    @Override
    public Response toResponse(Exception e) {
        logger.warn("Exception", e);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("cause", e.getClass().getName());

        com.xkyss.quarkus.rest.dto.Response<Object> r = com.xkyss.quarkus.rest.dto.Response.exception();
        r.setMessage(ems.getMessage(r.getCode()));
        r.setData(map);

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(r)
            .build();
    }
}

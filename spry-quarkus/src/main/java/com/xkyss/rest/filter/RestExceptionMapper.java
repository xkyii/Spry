package com.xkyss.rest.filter;

import com.xkyss.rest.error.RestException;
import com.xkyss.rest.service.ErrorMessageService;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ServerException异常处理
 */
@Provider
// @IfBuildProperty(name = "xkyss.server.build.exception-mapper.enabled", stringValue = "true")
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageService ems;

    @Override
    public Response toResponse(RestException e) {
        logger.warn("ServerException", e);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", e.getCode());
        map.put("message", ems.getMessage(e.getCode()));
        //noinspection DuplicatedCode
        map.put("cause", e.getClass().getName());

        com.xkyss.rest.dto.Response<Object> r = com.xkyss.rest.dto.Response.exception();
        r.setMessage(ems.getMessage(r.getCode()));
        r.setData(map);

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(r)
            .build();
    }
}

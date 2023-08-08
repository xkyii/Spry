package com.xkyii.spry.framework.filter;

import com.xkyss.quarkus.server.error.ServerException;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.INTERNAL_SERVER_ERROR;

@Provider
public class SpryExceptionMapper implements ExceptionMapper<ServerException> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageService ems;

    @Override
    public Response toResponse(ServerException e) {
        logger.warn("ServerException", e);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", INTERNAL_SERVER_ERROR);
        map.put("msg", ems.getMessage(e.getCode()));

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(map)
            .build();
    }
}

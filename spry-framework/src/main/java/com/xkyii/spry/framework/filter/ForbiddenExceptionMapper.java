package com.xkyii.spry.framework.filter;

import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import io.quarkus.security.ForbiddenException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Inject
    ErrorMessageService ems;

    @Override
    public Response toResponse(ForbiddenException exception) {
        AjaxResult r = new AjaxResult();
        r.setCode(Response.Status.FORBIDDEN.getStatusCode());
        r.setMessage(ems.getMessage(r.getCode()));

        return Response.status(Response.Status.OK)
            .type(MediaType.APPLICATION_JSON)
            .entity(r)
            .build();
    }
}
package com.xkyii.spry.admin.filter;

import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class Exception1Mapper implements ExceptionMapper<ValidationException> {
    @Inject
    Logger logger;
    @Override
    public Response toResponse(ValidationException e) {
        return Response.ok("vvvvvvvvvv").build();
    }
}

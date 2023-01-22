package com.xkyii.spry.admin.filter;


import org.hibernate.reactive.exception.ConstraintViolationException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.ws.rs.container.ContainerResponseContext;

class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<String> mapException(ConstraintViolationException x) {
        return RestResponse.status(RestResponse.Status.NOT_FOUND, "mapped Exception > " + x.getClass().getName());
    }

    @ServerResponseFilter
    public void  mapResponse(ContainerResponseContext context) {
        if ("true".equals(context.getHeaderString("validation-exception"))) {
            context.setEntity("mapped Response >" + context.getEntity().getClass().getName());
        }
    }

}
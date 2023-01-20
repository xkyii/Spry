package com.xkyii.spry.admin.filter;


import com.xkyii.spry.common.error.ApiException;
import org.hibernate.reactive.exception.ConstraintViolationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.RestResponse;

class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<String> mapException(ConstraintViolationException x) {
        return RestResponse.status(RestResponse.Status.NOT_FOUND, "hello > " + x.getClass().getName());
    }
}
package com.xkyii.spry.admin.filter;


import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ErrorMessageManager;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import org.hibernate.reactive.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerResponseContext;

@ApplicationScoped
class ExceptionMappers {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageManager emm;

    @ServerExceptionMapper
    public RestResponse<String> mapException(ConstraintViolationException x) {
        return RestResponse.status(RestResponse.Status.NOT_FOUND, "mapped Exception > " + x.getClass().getName());
    }

    @ServerResponseFilter
    public void  mapResponse(ContainerResponseContext context) {
        logger.info("mapResponse");

        // 参数校验异常
        if ("true".equals(context.getHeaderString("validation-exception"))) {
            context.setEntity("mapped Response >" + context.getEntity().getClass().getName());
        }

        if (context.hasEntity()) {
            Object entity = context.getEntity();
            // 对自定义Response填充message
            if (entity instanceof Response) {
                @SuppressWarnings("rawtypes") Response r = (Response) entity;
                if (r.getMessage() == null) {
                    r.setMessage(emm.getMessage(r.getCode()));
                }
                logger.info(Json.encodePrettily(entity));
            }
        }
    }

}
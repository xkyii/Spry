package com.xkyii.spry.admin.filter;

import io.quarkus.arc.log.LoggerName;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

/**
 * 输出Http请求体相关日志
 */
public class HttpLogFilter {
    @LoggerName("io.quarkus.http.access-log")
    Logger logger;

    @ServerResponseFilter(priority = Integer.MIN_VALUE+1)
    public void  mapResponse(ContainerRequestContext request, ContainerResponseContext response) {
        logger.info(request.getUriInfo().getPath());

        logger.infof("Response Status: %d", response.getStatus());
        if (response.hasEntity()) {

            Object entity = response.getEntity();
            logger.info(Json.encodePrettily(entity));
        }
    }
}

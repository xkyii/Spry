package com.xkyss.rest.filter;

import io.vertx.core.http.HttpServerResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerResponseContext;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@ApplicationScoped
public class ResponseFilter {

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response, HttpServerResponse resp) {
        // 没有返回体
        if (!response.hasEntity()) {
            return;
        }

    }
}

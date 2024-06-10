package com.xkyss.rest.filter;

import com.xkyss.rest.config.RuntimeConfig;
import com.xkyss.rest.dto.Response;
import io.quarkus.arc.properties.IfBuildProperty;
import io.vertx.core.http.HttpServerResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@Provider
@IfBuildProperty(name = "xkyss.rest.build.response-filter.enabled", stringValue = "true")
public class ResponseFilter {

    @Inject
    RuntimeConfig config;

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response, HttpServerResponse resp) {

        RuntimeConfig.ResponseFilterConfig filterConfig = checkFilterConfig();

        Response<Object> r = Response.success();
        try {
            // 包装一下
            // r.setMessage(ems.getMessage(r.getCode()));
            r.setData(response.getEntity());
        }
        finally {
            response.setEntity(r);
        }
    }

    RuntimeConfig.ResponseFilterConfig checkFilterConfig() {
        return null;
    }
}

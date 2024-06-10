package com.xkyss.rest.filter;

import com.xkyss.rest.config.BuildConfig;
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
    BuildConfig buildConfig;

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response, HttpServerResponse resp) {
        // 没有返回体
        if (!response.hasEntity()) {
            return;
        }

        // 包装一下
        Response<Object> r = Response.success();
        // r.setMessage(ems.getMessage(r.getCode()));
        r.setData(response.getEntity());
        response.setEntity(r);
    }
}

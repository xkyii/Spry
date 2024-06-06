package com.xkyss.rest.filter;

import com.xkyss.rest.dto.Response;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@Provider
public class ResponseFilter {

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response, HttpServerResponse resp, RoutingContext rc) {
        // 没有返回体
        if (!response.hasEntity()) {
            return;
        }

        // 不处理无媒体类型
        if (response.getMediaType() == null) {
            return;
        }

        // 包装一下
        Response<Object> r = Response.success();
        // r.setMessage(ems.getMessage(r.getCode()));
        r.setData(response.getEntity());
        response.setEntity(r);
    }
}

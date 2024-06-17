package com.xkyss.quarkus.rest.filter;

import com.xkyss.quarkus.rest.dto.Response;
import com.xkyss.quarkus.rest.config.RuntimeConfig;
import io.quarkus.arc.properties.IfBuildProperty;
import io.vertx.core.json.Json;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@ApplicationScoped
@IfBuildProperty(name = "xkyss.build.rest.response-filter.enabled", stringValue = "true")
public class ResponseFilter {

    private final List<RuntimeConfig.ResponseFilterConfig> responseFilterConfigs;

    public ResponseFilter(RuntimeConfig runtimeConfig) {
        Map<String, RuntimeConfig.ResponseFilterConfig> stringResponseFilterConfigMap = runtimeConfig.responseFilter();
        responseFilterConfigs = (stringResponseFilterConfigMap == null || stringResponseFilterConfigMap.isEmpty())
            ? null
            : stringResponseFilterConfigMap.values().stream()
                .filter(RuntimeConfig.ResponseFilterConfig::enabled)
                .toList();
    }

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response, ContainerRequestContext requestContext) {
        RuntimeConfig.ResponseFilterConfig filterConfig = checkFilterConfig(requestContext);
        if (filterConfig == null) {
            return;
        }
        // not content
        if (response.getStatus() == 204) {
            return;
        }

        Response<Object> r = Response.success();
        try {
            // 包装一下
            // r.setMessage(ems.getMessage(r.getCode()));
            r.setData(response.getEntity());
        }
        finally {
            // 没有指定类型,视为支持所有类型
            if (response.getMediaType() == null) {
                response.setEntity(r);
            }
            else {
                if (response.getMediaType().isCompatible(MediaType.TEXT_PLAIN_TYPE)) {
                    response.setEntity(Json.encode(r));
                }
                else {
                    response.setEntity(r);
                }
            }

        }
    }

    RuntimeConfig.ResponseFilterConfig checkFilterConfig(ContainerRequestContext requestContext) {
        if (responseFilterConfigs == null) {
            return null;
        }

        for (RuntimeConfig.ResponseFilterConfig responseFilterConfig : responseFilterConfigs) {
            // 匹配路径
            boolean matchPath = responseFilterConfig.path().isEmpty()
                || responseFilterConfig.path().get().equals("/*")
                || requestContext.getUriInfo().getPath().toLowerCase(Locale.ROOT).startsWith(responseFilterConfig.path().get());
            if (!matchPath) {
                return null;
            }

            // 匹配方法
            boolean matchMethod = responseFilterConfig.methods().isEmpty()
                || responseFilterConfig.methods().get().contains("*")
                || responseFilterConfig.methods().get().contains(requestContext.getMethod().toUpperCase(Locale.ROOT));
            if (!matchMethod) {
                return null;
            }

            return responseFilterConfig;
        }

        return null;
    }

}

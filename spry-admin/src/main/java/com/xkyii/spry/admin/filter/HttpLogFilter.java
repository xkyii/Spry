package com.xkyii.spry.admin.filter;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 输出Http请求体相关日志
 */
@ApplicationScoped
public class HttpLogFilter {

    @Inject
    Logger logger;

    @Inject
    Vertx vertx;

    @ServerResponseFilter
    public void  mapResponse(ContainerRequestContext request, ContainerResponseContext response) {
        vertx.executeBlocking(future -> {

            StringBuilder sb = new StringBuilder();

            sb.append(request.getMethod());
            sb.append(" ");
            sb.append(request.getUriInfo().getPath());

            sb.append("\n");
            sb.append(readAttribute(request.getHeaders()));

            if (request.hasEntity()) {
                try {
                    request.getEntityStream().reset();
                    String text = new BufferedReader(
                            new InputStreamReader(request.getEntityStream(), StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));
                    sb.append("\n\n");
                    sb.append(text);
                } catch (IOException e) {
                    // ignore
                }
            }

            sb.append("\n\n\n");
            sb.append(readAttribute(response.getStringHeaders()));
            if (response.hasEntity()) {
                sb.append("\n\n");
                Object entity = response.getEntity();
                if (entity instanceof String) {
                    sb.append(Json.encodePrettily(Json.decodeValue((String) entity)));
                }
                else {
                    sb.append(Json.encodePrettily(entity));
                }
            }

            logger.info(sb.toString());
            future.complete();
        });
    }

    private String readAttribute(MultivaluedMap<String, String> headers) {
        if (headers.isEmpty()) {
            return "-- No Headers";
        } else {
            final StringJoiner joiner = new StringJoiner(System.lineSeparator());

            for (Map.Entry<String, List<String>> header : headers.entrySet()) {
                final StringJoiner j2 = new StringJoiner(",");
                for (String h: header.getValue()) {
                    j2.add(h);
                }
                joiner.add(header.getKey() + ": " + j2);
            }

            return joiner.toString();
        }
    }
}

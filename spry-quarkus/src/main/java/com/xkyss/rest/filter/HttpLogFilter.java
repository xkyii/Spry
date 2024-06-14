package com.xkyss.rest.filter;

import com.xkyss.rest.config.RuntimeConfig;
import io.quarkus.runtime.util.StringUtil;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

import static com.xkyss.rest.constant.Constants.*;

@ApplicationScoped
public class HttpLogFilter {
    @Inject
    Logger logger;

    @Inject
    RuntimeConfig config;

    @Inject
    Vertx vertx;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @ServerRequestFilter
    public void mapRequest(ContainerRequestContext request, RoutingContext rc) {
        Runnable prev = rc.get(KEY_RESPONSE_FUNC);
        Runnable func = () -> {
            try {
                if (prev != null) {
                    prev.run();
                }

                boolean isLogBody = request.hasEntity();
                if (isLogBody) {
                    String text = IOUtils.toString(request.getEntityStream(), StandardCharsets.UTF_8);
                    request.setEntityStream(IOUtils.toInputStream(text, StandardCharsets.UTF_8));
                    rc.put(KEY_REQUEST_BODY_HASHCODE, text.hashCode());

                    if ((request.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE))) {
                        rc.put(KEY_REQUEST_BODY, Json.encodePrettily(Json.decodeValue(text)));
                    }
                    else {
                        rc.put(KEY_REQUEST_BODY, text);
                    }
                }
                else {
                    rc.put(KEY_REQUEST_BODY, "<EMPTY>");
                }
            } catch (Exception e) {
                logger.warn("获取Request Body失败", e);
                rc.put(KEY_REQUEST_BODY, "<EXCEPTION>");
            }
        };

        rc.put(KEY_RESPONSE_FUNC, func);
    }


    @ServerResponseFilter(priority = PRIORITY_REST_HTTP_LOG)
    public void mapResponse(ContainerResponseContext response, RoutingContext rc) {
        Runnable prev = rc.get(KEY_RESPONSE_FUNC);
        Runnable func = () -> {
            try {
                if (prev != null) {
                    prev.run();
                }

                boolean isLogBody = response.hasEntity();
                if (isLogBody) {
                    Object entity = response.getEntity();
                    if (entity instanceof String) {
                        rc.put(KEY_RESPONSE_BODY, entity);
                    }
                    else if (entity instanceof byte[]) {
                        rc.put(KEY_RESPONSE_BODY, Json.encodePrettily(Json.decodeValue(Buffer.buffer((byte[]) entity))));
                    }
                    else {
                        rc.put(KEY_RESPONSE_BODY, Json.encodePrettily(entity));
                    }
                } else {
                    rc.put(KEY_RESPONSE_BODY, "<EMPTY>");
                }
            } catch (Exception e) {
                logger.warn("获取Response Body失败", e);
                rc.put(KEY_RESPONSE_BODY, "<EXCEPTION>");
            }
        };

        rc.put(KEY_RESPONSE_FUNC, func);
    }

    void register(@Observes Router router) {
        LoggerHandler loggerHandler = new LoggerHandler(logger);

        router.route().order(Integer.MIN_VALUE).handler(loggerHandler);

        logger.info("已注册Http日志过滤.");
    }

    public static class LoggerHandler implements Handler<RoutingContext> {
        private final Logger logger;

        public LoggerHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void handle(RoutingContext rc) {
            rc.put(KEY_REQUEST_TIME, new Date());
            rc.put(KEY_REQUEST_BODY, "<EMPTY0>");
            rc.put(KEY_RESPONSE_BODY, "<EMPTY0>");

            Runnable func = () -> {
                StringBuilder sb = new StringBuilder();
                Date requestAt = rc.get(KEY_REQUEST_TIME);

                // request
                {
                    HttpServerRequest request = rc.request();
                    sb.append("\n\n[Request]:\n");
                    sb.append("[Request At]: ").append(dateFormat.format(requestAt)).append("\n\n");
                    sb.append(request.method()).append(" ").append(request.uri()).append(" ").append(request.version().alpnName()).append("\n");
                    String text = readAttribute(request.headers());
                    sb.append(StringUtil.isNullOrEmpty(text) ? "<Request head is empty>" : text).append("\n\n");
                    sb.append((String) rc.get(KEY_REQUEST_BODY));
                }

                // response
                {
                    HttpServerResponse response = rc.response();
                    Date responseAt = new Date();
                    sb.append("\n\n\n[Response]:\n");
                    sb.append("[ Request At]: ").append(dateFormat.format(requestAt)).append("\n");
                    sb.append("[Response At]: ").append(dateFormat.format(responseAt)).append("\n");
                    sb.append("[ Total Cast]: ").append(responseAt.getTime() - requestAt.getTime()).append(" ms\n\n");
                    sb.append("Status: ").append(response.getStatusCode()).append(", ").append(response.getStatusMessage()).append("\n");
                    String text = readAttribute(response.headers());
                    sb.append(StringUtil.isNullOrEmpty(text) ? "<Response head is empty>" : text).append("\n\n");
                    sb.append((String) rc.get(KEY_RESPONSE_BODY));
                }

                logger.info(sb);
            };

            rc.addEndHandler(_ -> {
                Runnable prev = rc.get(KEY_RESPONSE_FUNC);
                rc.vertx().executeBlocking(() -> {
                    if (prev != null) {
                        prev.run();
                    }
                    func.run();
                    return true;
                }, true);
            });

            rc.next();
        }

        private static String readAttribute(MultiMap headers) {
            if (headers.isEmpty()) {
                return null;
            }
            else {
                final StringJoiner joiner = new StringJoiner(System.lineSeparator());

                for (Map.Entry<String, String> header : headers) {
                    joiner.add(header.getKey() + ": " + header.getValue());
                }

                return joiner.toString();
            }
        }
    }
}

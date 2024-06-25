package com.xkyss.quarkus.rest.filter;

import com.xkyss.quarkus.rest.config.RuntimeConfig;
import com.xkyss.quarkus.rest.constant.Constants;
import io.quarkus.arc.properties.IfBuildProperty;
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
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

@ApplicationScoped
@IfBuildProperty(name = "xkyss.rest.build.http-log-filter.enabled", stringValue = "true")
public class HttpLogFilter {
    @Inject
    Logger logger;

    @Inject
    RuntimeConfig config;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @ServerRequestFilter
    public void mapRequest(ContainerRequestContext request, RoutingContext rc) {
        try {
            LogEntity le = rc.get(Constants.KEY_LOG_ENTITY);
            boolean isLogBody = request.hasEntity();
            if (isLogBody) {
                String text = IOUtils.toString(request.getEntityStream(), StandardCharsets.UTF_8);
                request.setEntityStream(IOUtils.toInputStream(text, StandardCharsets.UTF_8));
                le.requestBody = text;
            }
        } catch (Exception e) {
            logger.warn("获取Request Body失败", e);
        }
    }


    @ServerResponseFilter(priority = Constants.PRIORITY_REST_HTTP_LOG)
    public void mapResponse(ContainerResponseContext response, RoutingContext rc) {
        LogEntity le = rc.get(Constants.KEY_LOG_ENTITY);
        boolean isLogBody = response.hasEntity();
        if (isLogBody) {
            le.responseBody = response.getEntity();
        }
    }

    void register(@Observes Router router) {
        LoggerHandler loggerHandler = new LoggerHandler(logger);

        // router.route().order(Integer.MIN_VALUE).handler(loggerHandler);
        router.route("/api/*").order(Integer.MIN_VALUE).handler(loggerHandler);

        logger.info("已注册Http日志过滤.");
    }

    static class LogEntity {
        public Date requestTime;
        public String requestBody;
        public Object responseBody;
    }

    public static class LoggerHandler implements Handler<RoutingContext> {
        private final Logger logger;

        public LoggerHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void handle(RoutingContext rc) {
            {
                LogEntity le = new LogEntity();
                le.requestTime = new Date();
                rc.put(Constants.KEY_LOG_ENTITY, le);
            }

            Runnable func = () -> {
                StringBuilder sb = new StringBuilder();
                LogEntity le = rc.get(Constants.KEY_LOG_ENTITY);

                // request
                HttpServerRequest request = rc.request();
                {
                    sb.append("\n\n[Request]:\n");
                    sb.append(request.method()).append(" ").append(request.uri()).append(" ").append(request.version().alpnName()).append("\n");
                    String text = readAttribute(request.headers());
                    sb.append(StringUtil.isNullOrEmpty(text) ? "<Request head is empty>" : text).append("\n\n");
                    sb.append(le.requestBody == null ? "<EMPTY>" : le.requestBody);
                }

                // response
                HttpServerResponse response = rc.response();
                {
                    sb.append("\n\n\n[Response]:\n");
                    sb.append("Status: ").append(response.getStatusCode()).append(", ").append(response.getStatusMessage()).append("\n");
                    String text = readAttribute(response.headers());
                    sb.append(StringUtil.isNullOrEmpty(text) ? "<Response head is empty>" : text).append("\n\n");
                    sb.append(prettyBody(le.responseBody));
                }

                // extra
                {
                    Date responseAt = new Date();
                    sb.append("\n\n⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡⇡\n");
                    sb.append("[ URI Method]: ").append(request.method()).append("\n");
                    sb.append("[   URI Path]: ").append(request.absoluteURI()).append("\n");
                    sb.append("[ Request At]: ").append(dateFormat.format(le.requestTime)).append("\n");
                    sb.append("[Response At]: ").append(dateFormat.format(responseAt)).append("\n");
                    sb.append("[ Total Cast]: ").append(responseAt.getTime() - le.requestTime.getTime()).append(" ms\n");
                    sb.append("-------------------------------\n\n");
                }

                logger.info(sb);
            };

            rc.addEndHandler(_ -> {
                rc.vertx().executeBlocking(() -> {
                    func.run();
                    // logger.info("rc: " + rc);
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

        private static String prettyBody(Object body) {
            return switch (body) {
                case null -> "<EMPTY>";
                case String s -> s;
                case byte[] bytes -> Json.encodePrettily(Json.decodeValue(Buffer.buffer(bytes)));
                default -> Json.encodePrettily(body);
            };
        }
    }
}

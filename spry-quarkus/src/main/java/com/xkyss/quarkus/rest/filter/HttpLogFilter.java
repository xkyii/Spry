package com.xkyss.quarkus.rest.filter;

import com.xkyss.quarkus.rest.config.RuntimeConfig;
import com.xkyss.quarkus.rest.constant.Constants;
import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.runtime.util.StringUtil;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
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
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.xkyss.quarkus.rest.constant.Constants.KEY_LOG_ENTITY;

@ApplicationScoped
@IfBuildProperty(name = "xkyss.build.rest.http-log-filter.enabled", stringValue = "true")
public class HttpLogFilter {
    @Inject
    Logger logger;

    @Inject
    RuntimeConfig config;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @ServerRequestFilter
    public void mapRequest(ContainerRequestContext request, RoutingContext rc) {
        LogEntity le = rc.get(KEY_LOG_ENTITY);
        if (le == null) {
            // 代表不需要
            return;
        }

        try {
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
        LogEntity le = rc.get(KEY_LOG_ENTITY);
        if (le == null) {
            // 代表不需要
            return;
        }
        boolean isLogBody = response.hasEntity();
        if (isLogBody) {
            le.responseBody = response.getEntity();
        }
    }

    void register(@Observes Router router) {
        LoggerHandler loggerHandler = new LoggerHandler(logger);

        Map<String, RuntimeConfig.HttpLogFilterConfig> stringHttpLogFilterConfigMap = config.httpLogFilter();
        if (stringHttpLogFilterConfigMap.isEmpty()) {
            return;
        }

        for (RuntimeConfig.HttpLogFilterConfig config: stringHttpLogFilterConfigMap.values()) {
            if (!config.enabled()) {
                continue;
            }
            // 匹配路径
            // 空视为全部
            Route route = config.path().isEmpty() ? router.route() : router.route(config.path().get());
            route.order(Integer.MIN_VALUE);

            // 匹配方法
            // 空,'*'都视为全部
            do {

                if (config.methods().isEmpty()) {
                    break;
                }
                List<String> methods = config.methods().get();
                if (methods.isEmpty()) {
                    break;
                }
                if (methods.contains("*")) {
                    break;
                }

                for (String method: config.methods().get()) {
                    route.method(HttpMethod.valueOf(method));
                }
            } while (false);


            route.handler(loggerHandler);
        }

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
                rc.put(KEY_LOG_ENTITY, le);
            }

            Runnable func = () -> {
                StringBuilder sb = new StringBuilder();
                LogEntity le = rc.get(KEY_LOG_ENTITY);

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

            rc.addEndHandler(x -> {
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

package com.xkyii.spry.admin;

import com.xkyii.spry.common.config.SpryConfig;
import com.xkyii.spry.common.error.ErrorMessageManager;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.http.runtime.filters.QuarkusRequestWrapper;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;


@ApplicationScoped
public class SpryAdminApplication {

    @Inject
    SpryConfig config;

    void onStart(@Observes StartupEvent ev) {
        System.out.println(String.format("\n\t%s启动成功, 当前版本v%s, @%s\n",
            config.name(), config.version(), config.copyrightYear()));
    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.printf(String.format("\n\t%s已关闭.\n\n", config.name()));
    }

    @Produces
    ErrorMessageManager getErrorMessageManager() {
        return new ErrorMessageManager();
    }



    public void onRouter(@Observes Router router) {
        System.out.println("onRouter");
        router.route().order(Integer.MIN_VALUE).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter min"));
            rc.next();
        });
        router.route().order(Integer.MIN_VALUE+1).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter min+1"));
            rc.next();
        });
        router.route().order(Integer.MIN_VALUE+1).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter min++1"));
            rc.next();
        });
        router.route().order(Integer.MIN_VALUE+2).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter min+2"));
            rc.next();
        });
        router.route().order(-1).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter -1"));
            rc.next();
        });
        router.route().order(0).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter 0"));
            rc.next();
        });
        router.route().order(1).handler(rc -> {
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter 1"));
            QuarkusRequestWrapper.get(rc.request()).addRequestDoneHandler(event -> System.out.println("onRouter +1"));
            rc.next();
        });
    }
}

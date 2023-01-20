package com.xkyii.spry.admin;

import com.xkyii.spry.common.config.SpryConfig;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

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
}

package com.xkyss.rest.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.xkyss.rest.constant.Constants.CONFIG_REST_PREFIX;

@ConfigMapping(prefix = CONFIG_REST_PREFIX)
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface RuntimeConfig {

    Map<String, ResponseFilterConfig> responseFilter();

    interface ResponseFilterConfig {

        @WithDefault("true")
        boolean enabled();

        @WithDefault("/*")
        String path();

        @WithDefault("*")
        Optional<List<String>> methods();
    }
}

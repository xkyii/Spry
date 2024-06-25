package com.xkyss.quarkus.rest.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithConverter;
import io.smallrye.config.WithDefault;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.xkyss.quarkus.rest.constant.Constants.CONFIG_REST_PREFIX;

@ConfigMapping(prefix = CONFIG_REST_PREFIX)
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface RuntimeConfig {

    Map<String, HttpLogFilterConfig> httpLogFilter();

    Map<String, ResponseFilterConfig> responseFilter();

    interface HttpLogFilterConfig {

        @WithDefault("true")
        boolean enabled();

        @WithDefault("/*")
        @WithConverter(LowerConverter.class)
        Optional<String> path();

        @WithDefault("*")
        @WithConverter(UpperListConverter.class)
        Optional<List<String>> methods();
    }

    interface ResponseFilterConfig {

        @WithDefault("true")
        boolean enabled();

        @WithDefault("/*")
        @WithConverter(LowerConverter.class)
        Optional<String> path();

        @WithDefault("*")
        @WithConverter(UpperListConverter.class)
        Optional<List<String>> methods();
    }
}

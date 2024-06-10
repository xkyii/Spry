package com.xkyss.rest.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import static com.xkyss.rest.constant.Constants.CONFIG_REST_BUILD_PREFIX;

@ConfigMapping(prefix = CONFIG_REST_BUILD_PREFIX)
@ConfigRoot(phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public interface BuildConfig {

    /**
     * [ResponseFilter]配置
     */
    ResponseFilterConfig responseFilter();

    /**
     * [ExceptionMapper]配置
     */
    ExceptionMapperConfig exceptionMapper();


    interface ResponseFilterConfig {

        /**
         * 是否启用[ResponseFilter]
         */
        @WithDefault("false")
        boolean enabled();
    }

    interface ExceptionMapperConfig {
        /**
         * 是否启用[ExceptionMapper]
         */
        @WithDefault("false")
        boolean enabled();
    }
}

package com.xkyii.spry.web.config;

import io.smallrye.config.ConfigMapping;

import static com.xkyii.spry.web.constant.Constants.ADMIN_CONFIG_PREFIX;

@ConfigMapping(prefix = ADMIN_CONFIG_PREFIX)
public interface AdminConfig {

    /**
     * 开发模式配置
     */
    DevConfig dev();

    interface DevConfig {

        /**
         * Token ID, aka jti
         */
        String tokenId();
    }
}

package com.xkyii.spry.web.config;

import io.smallrye.config.ConfigMapping;

import static com.xkyii.spry.web.constant.Constants.ADMIN_CONFIG_PREFIX;

@ConfigMapping(prefix = ADMIN_CONFIG_PREFIX)
public interface AdminConfig {
}

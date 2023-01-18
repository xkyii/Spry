package com.xkyii.spry.common.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "spry")
public interface SpryConfig {

    /**
     * 项目名称
     */
    String name();

    /**
     * 版本
     */
    String version();

    /**
     * 版权年份
     */
    String copyrightYear();
}

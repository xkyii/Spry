package com.xkyii.spry.common.config;


import io.smallrye.config.ConfigMapping;

import java.util.Map;

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

    /**
     * 错误号
     */
    Map<Integer, String> errorCodes();

    default String getCodeMessage(Integer code) {
        if (errorCodes().containsKey(code)) {
            return errorCodes().get(code);
        }

        return String.format("UnConfigured Error(%d)", code);
    }
}


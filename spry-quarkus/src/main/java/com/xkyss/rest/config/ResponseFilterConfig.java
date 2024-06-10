package com.xkyss.rest.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

import java.util.List;
import java.util.Optional;

@ConfigGroup
public class ResponseFilterConfig {

    @ConfigItem(defaultValue = "true")
    public boolean enabled;

    @ConfigItem(defaultValue = "/*")
    public String path;

    @ConfigItem
    public Optional<List<String>> methods;
}

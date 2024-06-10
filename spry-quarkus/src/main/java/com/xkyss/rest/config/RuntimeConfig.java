package com.xkyss.rest.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Map;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface RuntimeConfig {

    Map<String, ResponseFilterConfig> responseFilter;
}

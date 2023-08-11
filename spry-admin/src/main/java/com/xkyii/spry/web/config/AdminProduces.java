package com.xkyii.spry.web.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

import java.util.concurrent.CompletableFuture;

import static com.xkyii.spry.web.constant.Constants.ADMIN_CACHE_NAME_LOGIN_USER;
import static com.xkyii.spry.web.constant.Constants.ADMIN_STARTUP_PRIORITY;

@Singleton
public class AdminProduces {
    @Inject
    Logger logger;

    @Inject
    AdminConfig adminConfig;

    @Inject
    ObjectMapper objectMapper;

    public void onStartup(@Observes @Priority(ADMIN_STARTUP_PRIORITY) StartupEvent e) {
        /// 打印配置
        ObjectMapper mapper = objectMapper.copy();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            logger.infof("\nAdminConfig: \n%s", mapper.writeValueAsString(adminConfig));
        } catch (JsonProcessingException ex) {
            logger.warn("配置格式错误", ex);
        }
    }
}

package com.xkyii.spry.web.config;

import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.model.LoginUser;
import com.xkyii.spry.web.service.SysPermissionService;
import com.xkyss.quarkus.server.error.ServerException;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.VertxContextSupport;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.concurrent.CompletableFuture;

import static com.xkyii.spry.web.constant.AdminError.模拟登录失败;
import static com.xkyii.spry.web.constant.Constants.ADMIN_CACHE_NAME_LOGIN_USER;
import static com.xkyii.spry.web.constant.Constants.STARTUP_PRIORITY_DEV;

/**
 * 仅开发模式有效的一些处理
 *
 * @author xkyii
 * @since 2023-08-11
 */
@IfBuildProfile("dev")
public class DevProduces {
    @Inject
    Logger logger;

    @Inject
    SysPermissionService permissionService;

    @Inject
    AdminConfig adminConfig;

    @CacheName(ADMIN_CACHE_NAME_LOGIN_USER)
    Cache cache;

    /**
     * 开发模式下,每次热启动都会清理Cache
     * 由于登录用户LoginUser在每次/login时缓存,修改代码之后自动热启动,就需要手动登录一次,如果使用其他idea http client来进行测试就会变得很繁琐.
     * 这里自动做一次LoginUser的缓存,缓存key需要与登录的token id一致
     * 由于登录时的jti是随机生成,所以需要手动配置一下
     */
    public void onStartup(
        @Observes @Priority(STARTUP_PRIORITY_DEV)
        StartupEvent e) {
        logger.info("dev startup");
        logger.info("\t模拟[admin]登录");

        SysUser user = new SysUser();
        user.setUserName("admin");
        user.setPassword("admin123");
        try {
            VertxContextSupport.subscribeAndAwait(() -> permissionService.getRolePermission(user)
                .onItem().transform(permissions -> new LoginUser(user).withPermissions(permissions))
                .onItem().invoke(loginUser -> {
                    cache.as(CaffeineCache.class).put(adminConfig.dev().tokenId(), CompletableFuture.completedFuture(loginUser));
                    logger.infof("\t已缓存LoginUser, jti: %s", adminConfig.dev().tokenId());
                }));
        } catch (Throwable ex) {
            throw new ServerException(模拟登录失败, ex);
        }
    }
}

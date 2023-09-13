package com.xkyii.spry.web.service;

import com.xkyii.spry.web.config.AdminConfig;
import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.model.LoginUser;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.xkyii.spry.web.constant.Constants.ADMIN_CACHE_NAME_LOGIN_USER;

@ApplicationScoped
public class TokenService {

    @Inject
    Logger logger;

    @Inject
    AdminConfig adminConfig;

    @CacheName(ADMIN_CACHE_NAME_LOGIN_USER)
    Cache cache;

    /**
     * 生成一个token
     */
    public String generateToken(LoginUser loginUser) {
        SysUser user = loginUser.getUser();
        String jti = adminConfig.dev().enabled() ? adminConfig.dev().tokenId() : UUID.randomUUID().toString();
        logger.infof("生成token, jti: %s", jti);

        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUserName());
        claims.claim(Claims.email, StringUtil.isNullOrEmpty(user.getEmail())
            ? (user.getUserName() + "@xkyii.com")
            : user.getEmail());
        claims.claim(Claims.jti, jti);
        claims.groups(new HashSet<>(loginUser.getPermissions()));
        // 过期时间
        claims.expiresIn(86400);

        cache.as(CaffeineCache.class).put(jti, CompletableFuture.completedFuture(loginUser));

        return claims.sign();
    }
}

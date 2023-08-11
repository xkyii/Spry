package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.model.LoginUser;
import com.xkyii.spry.web.repository.SysUserRepository;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheManager;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.xkyii.spry.web.constant.Constants.CACHE_NAME_LOGIN_USER;

@ApplicationScoped
public class TokenService {

    @CacheName(CACHE_NAME_LOGIN_USER)
    Cache cache;

    @Inject
    SysUserRepository userRepository;

    /**
     * 生成一个token
     */
    public String generateToken(LoginUser loginUser) {
        SysUser user = loginUser.getUser();
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUserName());
        claims.claim(Claims.email, StringUtil.isNullOrEmpty(user.getEmail())
            ? (user.getUserName() + "@xkyii.com")
            : user.getEmail());
        claims.claim(Claims.nonce, UUID.randomUUID().toString());
        claims.groups(new HashSet<>(loginUser.getPermissions()));
        // 过期时间
        claims.expiresIn(86400);

        cache.as(CaffeineCache.class).put("", CompletableFuture.completedFuture(loginUser));

        return claims.sign();
    }
}

package com.xkyii.spry.web.filter;

import com.xkyss.quarkus.server.error.ServerException;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import static com.xkyii.spry.web.constant.AdminError.Token已失效;
import static com.xkyii.spry.web.constant.Constants.CACHE_NAME_LOGIN_USER;
import static com.xkyii.spry.web.constant.Constants.CONTEXT_KEY_LOGIN_USER;

/**
 * 给请求添加用户信息
 */
@ApplicationScoped
public class AuthedAugmentor implements SecurityIdentityAugmentor {

    @CacheName(CACHE_NAME_LOGIN_USER)
    Cache cache;

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        // 匿名用户直接通过
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }

        // 只处理jwt
        if (!(identity.getPrincipal() instanceof JsonWebToken)) {
            return Uni.createFrom().item(identity);
        }

        JsonWebToken jwt = (JsonWebToken) identity.getPrincipal();

        return cache.get(jwt.getTokenID(), tokenID -> null)
            .onItem().ifNull().failWith(new ServerException(Token已失效))
            .onItem().transform(loginUser -> QuarkusSecurityIdentity.builder(identity)
                .addAttribute(CONTEXT_KEY_LOGIN_USER, loginUser)
                .build()
            );
    }
}

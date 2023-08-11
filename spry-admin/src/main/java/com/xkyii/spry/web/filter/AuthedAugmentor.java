package com.xkyii.spry.web.filter;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;

/**
 * 给请求添加用户信息
 */
@ApplicationScoped
public class AuthedAugmentor implements SecurityIdentityAugmentor {

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        // 匿名用户直接通过
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }

        // 填充用户信息
        return Uni.createFrom().item(QuarkusSecurityIdentity.builder(identity).build());
    }
}

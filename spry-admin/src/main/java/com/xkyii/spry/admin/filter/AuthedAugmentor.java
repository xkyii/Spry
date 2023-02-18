package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.repository.SysUserRepository;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import java.util.function.Supplier;

@ApplicationScoped
public class AuthedAugmentor implements SecurityIdentityAugmentor {

    @Inject
    SysUserRepository userRepository;

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }

        return userRepository.get(identity.getPrincipal().getName())
            .onItem().ifNotNull().<SecurityIdentity>transform(user -> {
                JsonObject attributes = new JsonObject()
                    .put("email", user.getEmail());
                return QuarkusSecurityIdentity.builder()
                    .setPrincipal(identity.getPrincipal())
                    .addAttributes(attributes.getMap())
//                    .addRoles()
//                    .addPermissions()
                    .build();
            })
            .onItem().ifNull().continueWith(identity)
            ;


    }

    @ActivateRequestContext
    Supplier<SecurityIdentity> build(SecurityIdentity identity) {
        QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);
        String username = identity.getPrincipal().getName();

        userRepository.get(username).subscribe().with(user -> {
            builder.addAttribute("000", "000000");
        });

//        UserRoleEntity.<userRoleEntity>streamAll()
//            .filter(role -> user.equals(role.user))
//            .forEach(role -> builder.addRole(role.role));

        return builder::build;
    }
}

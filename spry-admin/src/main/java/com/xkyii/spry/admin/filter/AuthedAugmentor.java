package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.dto.auth.LoginUser;
import com.xkyii.spry.admin.dto.auth.RoleInfo;
import com.xkyii.spry.admin.entity.SysRole;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysMenuRepository;
import com.xkyii.spry.admin.repository.SysRoleRepository;
import com.xkyii.spry.admin.repository.SysUserRepository;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import java.util.Set;


@ApplicationScoped
public class AuthedAugmentor implements SecurityIdentityAugmentor {

    public static final String LOGIN_USER = LoginUser.class.getName();

    @Inject
    SysUserRepository userRepository;

    @Inject
    SysMenuRepository menuRepository;

    @Inject
    SysRoleRepository roleRepository;

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }

        return Uni.createFrom().item(new Context())
            .flatMap(ctx -> userRepository.get(identity.getPrincipal().getName()).map(ctx::setUser))
            .flatMap(ctx -> roleRepository.getRoleOfUser(ctx.getUserId()).map(ctx::setRole))
            .flatMap(ctx -> menuRepository.getPermissionsOfUser(ctx.getUserId()).map(ctx::setMenuPermissions))
            .map(ctx -> QuarkusSecurityIdentity.builder(identity)
                .addAttribute(LOGIN_USER, new LoginUser(ctx.user, new RoleInfo(
                    ctx.role,
                    ctx.role.getRoleKey(),
                    ctx.menuPermissions,
                    ctx.menuIds
                )))
                .build()
            );
    }

    private static class Context {
        private SysUser user;
        private SysRole role;
        private Set<String> menuPermissions;
        private Set<Long> menuIds;

        public Long getUserId() {
            return (user == null || user.getUserId() == null) ? 0L : user.getUserId();
        }

        public Context setUser(SysUser user) {
            this.user = user;
            return this;
        }

        public Context setRole(SysRole role) {
            this.role = role;
            return this;
        }

        public Context setMenuPermissions(Set<String> permissions) {
            this.menuPermissions = permissions;
            return this;
        }

        public Context setMenuIds(Set<Long> menuIds) {
            this.menuIds = menuIds;
            return this;
        }
    }
}

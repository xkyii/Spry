package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysRole;
import com.xkyii.spry.web.entity.SysUser;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class SysPermissionService {

    @Inject
    SysRoleService roleService;

    @Inject
    SysMenuService menuService;

    /**
     * 获取用户的权限列表
     */
    @WithSession
    public Uni<Set<String>> getRolePermission(SysUser user) {
        // 管理员拥有所有权限
        return user.isAdmin()
            ? Uni.createFrom().item(Set.of("admin"))
            : roleService.selectRolePermissionByUserId(user.getUserId());
    }

    public Uni<Set<String>> getMenuPermission(SysUser user) {
        if (user.isAdmin()) {
            return Uni.createFrom().item(Set.of("*:*:*"));
        }

        List<SysRole> roles = user.getRoles();
        if (roles == null || roles.isEmpty()) {
            return menuService.selectMenuPermsByUserId(user.getUserId());
        }

        Set<String> perms = new HashSet<>();
        return Uni.combine().all()
            .unis(roles.stream()
                .map(role -> menuService.selectMenuPermsByRoleId(role.getRoleId()))
                .collect(Collectors.toList()))
            .combinedWith(permsLists -> {
                // TODO: 未完成
                // permsLists.forEach(list -> perms.addAll(list));
                return perms;
            });

    }
}


package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysMenu;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SysMenuRepository implements PanacheRepository<SysMenu> {
    private final String CACHE_NAME = "sys_menu";

    @CacheResult(cacheName = CACHE_NAME)
    public Uni<Set<String>> getPermissionsOfUser(Long userId) {
        return getSession().flatMap(session ->
            session.createNamedQuery("SysMenu.getPermissionsOfUser", String.class)
                .setParameter(1, userId)
                .getResultList()
                .map(permissions -> {
                    Set<String> set = new HashSet<>();
                    for (String permission: permissions) {
                        if (!StringUtil.isNullOrEmpty(permission)) {
                            set.add(permission);
                        }
                    }
                    return set;
                }));
    }
}

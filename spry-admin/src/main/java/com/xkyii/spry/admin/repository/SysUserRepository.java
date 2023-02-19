
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysUser;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

@ApplicationScoped
public class SysUserRepository implements PanacheRepository<SysUser> {
    private final String CACHE_NAME = "sys-user";

    @CacheResult(cacheName = CACHE_NAME)
    public Uni<SysUser> get(Long id) {
        return findById(id);
    }

    @CacheResult(cacheName = CACHE_NAME)
    public Uni<SysUser> get(String username) {
        return find("username", username).firstResult();
    }

    public Uni<Set<String>> getMenuPermissionsOf(Long userId) {
        return null;
    }
}

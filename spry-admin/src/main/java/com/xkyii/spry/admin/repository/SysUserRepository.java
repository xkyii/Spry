
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysUser;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysUserRepository implements PanacheRepository<SysUser> {

    @CacheResult(cacheName = "sys-user")
    public Uni<SysUser> get(Long id) {
        return findById(id);
    }
}

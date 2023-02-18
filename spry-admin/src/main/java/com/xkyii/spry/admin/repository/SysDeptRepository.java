
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysDept;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysDeptRepository implements PanacheRepository<SysDept> {

    @CacheResult(cacheName = "sys-dept")
    public Uni<SysDept> get(Long id) {
        return findById(id);
    }

}

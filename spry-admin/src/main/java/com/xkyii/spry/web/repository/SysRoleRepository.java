package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysRole;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SysRoleRepository implements PanacheRepository<SysRole> {
    public Uni<List<SysRole>> selectRolePermissionByUserId(Long userId) {
        Map<String, Object> parameters = Parameters.with("userId", userId).map();

        return find("#SysRole.selectRolePermissionByUserId", parameters)
            .list();
    }
}

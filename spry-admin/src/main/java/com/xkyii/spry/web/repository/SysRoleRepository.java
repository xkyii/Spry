package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysRole;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SysRoleRepository implements PanacheRepository<SysRole> {
    public Uni<List<SysRole>> selectRolePermissionByUserId(Long userId) {
        return find("#SysRole.selectRolePermissionByUserId").list();
    }
}

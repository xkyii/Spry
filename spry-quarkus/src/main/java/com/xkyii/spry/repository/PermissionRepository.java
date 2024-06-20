package com.xkyii.spry.repository;

import com.xkyii.spry.entity.Permission;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PermissionRepository implements PanacheRepository<Permission> {
}

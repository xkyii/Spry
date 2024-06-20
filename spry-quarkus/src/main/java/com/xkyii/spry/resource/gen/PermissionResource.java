package com.xkyii.spry.resource.gen;

import com.xkyii.spry.entity.Permission;
import com.xkyii.spry.repository.PermissionRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/api/permission")
public interface PermissionResource extends PanacheRepositoryResource<PermissionRepository, Permission, Long> {
}

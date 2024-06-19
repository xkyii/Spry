package com.xkyii.spry.resource.gen;

import com.xkyii.spry.entity.Role;
import com.xkyii.spry.repository.RoleRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/api/role")
public interface RoleResource extends PanacheRepositoryResource<RoleRepository, Role, Long> {
}

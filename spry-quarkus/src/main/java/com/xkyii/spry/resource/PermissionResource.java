package com.xkyii.spry.resource;

import com.xkyii.spry.entity.Permission;
import com.xkyii.spry.mapper.PermissionMapper;
import com.xkyii.spry.repository.PermissionRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import static com.xkyii.spry.error.ErrorCode.角色不存在;


@Path("/api/permission")
public class PermissionResource {

    @Inject
    PermissionRepository permissionRepository;

    @Inject
    PermissionMapper permissionMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Permission patch(@PathParam("id") Long id, Permission inPermission) {
        Permission dbPermission = permissionRepository.findById(id);
        if (dbPermission == null) {
            throw new RestException(角色不存在);
        }

        permissionMapper.update(dbPermission, inPermission);
        permissionRepository.persist(dbPermission);
        return dbPermission;
    }
}

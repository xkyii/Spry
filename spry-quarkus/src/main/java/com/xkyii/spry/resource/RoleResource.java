package com.xkyii.spry.resource;

import com.xkyii.spry.entity.Role;
import com.xkyii.spry.mapper.RoleMapper;
import com.xkyii.spry.repository.RoleRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import static com.xkyii.spry.error.ErrorCode.角色不存在;


@Path("/api/role")
public class RoleResource {

    @Inject
    RoleRepository roleRepository;

    @Inject
    RoleMapper roleMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Role patch(@PathParam("id") Long id, Role inRole) {
        Role dbRole = roleRepository.findById(id);
        if (dbRole == null) {
            throw new RestException(角色不存在);
        }

        roleMapper.update(dbRole, inRole);
        roleRepository.persist(dbRole);
        return dbRole;
    }
}

package com.xkyii.spry.web.resource;

import com.xkyii.spry.web.entity.SysRole;
import com.xkyii.spry.web.repository.SysRoleRepository;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

import static com.xkyii.spry.web.constant.Constants.ADMIN_ROUTER_PREFIX;

@IfBuildProfile("dev")
@Path(ADMIN_ROUTER_PREFIX + "/dev")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "DEV", description = "调试用")
@PermitAll
public class DevResource {

    @Inject
    SysRoleRepository sysRoleRepository;

    @GET
    @Path("selectRolePermissionByUserId")
    @Operation(summary = "获取SysRole")
    public Uni<List<SysRole>> selectRolePermissionByUserId(@RestQuery Long userId) {
        return sysRoleRepository.selectRolePermissionByUserId(userId);
    }
}

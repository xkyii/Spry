package com.xkyii.spry.admin.resource;

import com.xkyii.spry.common.config.SpryConfig;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;


@Path("")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class EntryResource {

    @Inject
    SpryConfig config;

    @GET
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }

    @GET
    @Path("test/permitall")
    @PermitAll
    public String permitall(@Context SecurityContext context) {
        return "permitall";
    }

    @GET
    @Path("test/denyall")
    @DenyAll
    public String denyall(@Context SecurityContext context) {
        return "denyall";
    }

    @GET
    @Path("test/authenticated")
    @Authenticated
    public String authenticated(@Context SecurityContext context) {
        return "authenticated";
    }

    @GET
    @Path("test/rolesallowed")
    @RolesAllowed("Admin")
    public String rolesallowed(@Context SecurityContext context) {
        return "rolesallowed";
    }
}

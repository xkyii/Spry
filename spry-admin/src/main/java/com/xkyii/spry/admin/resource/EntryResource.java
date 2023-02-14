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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Path("")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Tag(description = "应用入口点")
public class EntryResource {

    @Inject
    SpryConfig config;

    @GET
    @Operation(summary = "主入口", description = "当前应用的基本信息")
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }

    @GET
    @Path("test/permitall")
    @PermitAll
    @Operation(summary = "测试全权限", description = "@PermitAll 注解的资源无需权限就可以访问")
    public String permitall(@Context SecurityContext context) {
        return "permitall";
    }

    @GET
    @Path("test/denyall")
    @DenyAll
    @Operation(summary = "测试无权限", description = "@DenyAll 注解的资源不可访问")
    public String denyall(@Context SecurityContext context) {
        return "denyall";
    }

    @GET
    @Path("test/authenticated")
    @Authenticated
    @Operation(summary = "测试权限", description = "@Authenticated 注解的资源需要任意权限就访问")
    public String authenticated(@Context SecurityContext context) {
        return "authenticated";
    }

    @GET
    @Path("test/rolesallowed")
    @RolesAllowed("Admin")
    @Operation(summary = "测试角色权限", description = "@RolesAllowed 注解的资源需要有对应的角色权限才可以访问(当前为'Admin')")
    public String rolesallowed(@Context SecurityContext context) {
        return "rolesallowed";
    }
}

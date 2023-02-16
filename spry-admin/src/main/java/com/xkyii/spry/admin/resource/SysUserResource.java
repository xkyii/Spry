package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.*;
import com.xkyii.spry.admin.service.SysUserService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(description = "系统用户")
public class SysUserResource {
    @Inject
    SysUserService userService;

    @POST
    @Path("register")
    @Operation(summary = "注册用户", description = "注册用户")
    public Uni<RegisterOutput> register(@Valid RegisterInput input) {
        return userService.register(input);
    }

    @POST
    @Path("login")
    @Operation(summary = "登录用户", description = "登录用户")
    public Uni<TokenOutput> login(@Valid LoginInput loginInput) {
        return userService.login(loginInput);
    }


    @GET
    @Path("getLoginUserInfo")
    @Operation(summary = "获取用户信息", description = "获取当前已经登录的用户信息")
    @Authenticated
    public Uni<UserPermissionOutput> getLoginUserInfo() {

        return userService.getLoginUserInfo();
    }
}

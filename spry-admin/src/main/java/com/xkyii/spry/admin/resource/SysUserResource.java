package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.LoginInput;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import com.xkyii.spry.admin.dto.login.TokenOutput;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.admin.service.ITokenService;
import com.xkyii.spry.common.dto.Response;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(description = "系统用户")
public class SysUserResource {

    @Inject
    ISysUserService userService;

    @POST
    @Path("register")
    @Operation(summary = "注册用户", description = "注册用户")
    public Uni<Response<RegisterOutput>> register(RegisterInput input) {
        return userService.register(input)
                .onItem().transform(Response::ok);
    }

    @POST
    @Path("login")
    @Operation(summary = "登录用户", description = "登录用户")
    public Uni<Response<TokenOutput>> login(@Valid LoginInput loginInput) {
        return userService.login(loginInput)
                .onItem().transform(Response::ok);
    }
}

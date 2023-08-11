package com.xkyii.spry.web.resource;


import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.service.RuoYiHttpClient;
import com.xkyii.spry.web.service.SysUserService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import static com.xkyii.spry.web.constant.Constants.ROUTER_PREFIX;

@Path(ROUTER_PREFIX)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(description = "登录")
public class LoginController {

    @Inject
    Logger logger;

    @RestClient
    RuoYiHttpClient ruoyi;

    @Inject
    SysUserService userService;

    @GET
    @Path("captchaImage")
    public Response captchaImage() {
        return ruoyi.captchaImage();
    }

    @POST
    @Path("login")
    public Uni<AjaxResult> login(@Valid LoginCommand input) {
        return userService.login(input)
            .onItem().transform(output -> AjaxResult.success().put("token", output.getToken()));
    }

    // @POST
    // @Path("login")
    // public Response login(@Valid LoginCommand input) {
    //     return ruoyi.login(input);
    // }

    @POST
    @Path("logout")
    @Authenticated
    public Response logout() {
        return ruoyi.logout();
    }

    @GET
    @Path("getInfo")
    @DenyAll
    public Uni<AjaxResult> getInfo() {
        return userService.getInfo();
    }

    // @GET
    // @Path("getInfo")
    // public Response getInfo() {
    //     return ruoyi.getInfo();
    // }

    @GET
    @Path("getRouters")
    @Authenticated
    public Response getRouters() {
        return ruoyi.getRouters();
    }
}

package com.xkyii.spry.web.resource;


import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.web.service.RuoYiHttpClient;
import com.xkyii.spry.web.service.SysUserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
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
    public Uni<LoginOutput> login(LoginCommand input) {
        return userService.login(input);
    }

    @POST
    @Path("logout")
    public Response logout() {
        return ruoyi.logout();
    }

    @GET
    @Path("getInfo")
    public Response getInfo() {
        logger.info("getInfo");
        return ruoyi.getInfo();
    }
}

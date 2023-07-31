package com.xkyii.spry.web.resource;


import com.xkyii.spry.common.dto.entity.LoginQuery;
import com.xkyii.spry.web.service.RuoYiHttpClient;
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

    @RestClient
    RuoYiHttpClient ruoyi;

    @Inject
    Logger logger;

    @GET
    @Path("captchaImage")
    public Response captchaImage() {
        return Response.ok().build();
    }

    @POST
    @Path("login")
    public Response login(LoginQuery query) {
        return ruoyi.login(query);
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

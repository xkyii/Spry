package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.service.ISysUserService;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class SysUserResource {

    @Inject
    Logger logger;

    @Inject
    ISysUserService userService;


    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(@Valid RegisterInput input) {
        logger.infof("注册用户,入参: \n%s", Json.encodePrettily(input));
        return userService.register(input);
    }

}

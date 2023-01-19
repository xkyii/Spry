package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.service.SysUserService;
import com.xkyii.spry.common.config.SpryConfig;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
public class LoginResource {

    @Inject
    Logger logger;

    @Inject
    SpryConfig config;

    @Inject
    SysUserService userService;

    @GET
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(RegisterInput input) {
        logger.info(input);
        return "register";
    }

}

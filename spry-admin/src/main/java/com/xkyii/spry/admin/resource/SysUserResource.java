package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.dto.Response;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SysUserResource {

    @Inject
    Logger logger;

    @Inject
    ISysUserService userService;

    @POST
    @Path("register")
    @ReactiveTransactional
    public Uni<Response<RegisterOutput>> register(@Valid RegisterInput input) {
        logger.infof("注册用户,入参: \n%s", Json.encodePrettily(input));

        return userService.register(input)
                .onItem().transform(RegisterOutput::from)
                .onItem().transform(Response::ok);
    }
}

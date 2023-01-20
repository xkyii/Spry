package com.xkyii.spry.admin.resource;


import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.config.SpryConfig;
import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.common.resource.AResource;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SysUserResource extends AResource {

    @Inject
    Logger logger;

    @Inject
    ISysUserService userService;

    protected SysUserResource(SpryConfig config) {
        super(config);
    }

    @POST
    @Path("register")
    @ReactiveTransactional
    public Uni<Response<SysUser>> register(@Valid RegisterInput input) {
        logger.infof("注册用户,入参: \n%s", Json.encodePrettily(input));

        throw new ApiException(0, "fff");
//        return userService.register(input)
//                .onItem().transform(super::ok);
    }

}

package com.xkyii.spry.admin.resource;

import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.common.config.SpryConfig;
import com.xkyii.spry.common.error.ApiException;
import io.quarkus.runtime.util.StringUtil;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestQuery;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestResource {

    @Inject
    SpryConfig config;

    @Inject
    Logger logger;

    @GET
    @Path("exception")
    @Produces(MediaType.TEXT_PLAIN)
    public String exception() {
        throw new IllegalArgumentException("entry exception");
    }

    @GET
    @Path("errorCode")
    @Produces(MediaType.TEXT_PLAIN)
    public String errorCode() {
        logger.infof("errorCode: ", config.errorCodes().get(0));
        return config.errorCodes().get(0);
    }

    @POST
    @Path("validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validate(@Valid RegisterInput input) {
        return "validate";
    }

    @GET
    @Path("cheese")
    public String cheese(String cheese) {
        if(StringUtil.isNullOrEmpty(cheese))
            // send a 400
            throw new ApiException(0, "000");
        return cheese;
    }

    @GET
    @Path("i18n")
    @Produces(MediaType.TEXT_PLAIN)
    public String i18n(@RestQuery String key) {
        logger.infof("i18n key: %s", key);
//        return emm.getMessage(key);
        return "";
    }
}

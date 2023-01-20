package com.xkyii.spry.admin.resource;

import com.xkyii.spry.common.config.SpryConfig;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class EntryResource {

    @Inject
    SpryConfig config;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }

    @GET
    @Path("exception")
    @Produces(MediaType.TEXT_PLAIN)
    public String exception() {
        throw new IllegalArgumentException("entry");
    }

    @GET
    @Path("errorCode")
    @Produces(MediaType.TEXT_PLAIN)
    public String errorCode() {
        logger.infof("errorCode: ", config.errorCodes().get(0));
        return config.errorCodes().get(0);
    }
}

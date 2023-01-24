package com.xkyii.spry.admin.resource;

import com.xkyii.spry.common.config.SpryConfig;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class EntryResource {

    @Inject
    SpryConfig config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }
}

package com.xkyii.spry.admin.resource;

import com.xkyii.spry.common.config.SpryConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("")
public class EntryResource {

    @Inject
    SpryConfig config;

    @GET
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }
}

package com.xkyii.spry.resource;

import com.xkyii.spry.config.SpryConfig;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class LoginResource {

    @Inject
    SpryConfig config;

    @GET
    public String index() {
        return String.format("欢迎使用%s, 当前版本: v%s @%s", config.name(), config.version(), config.copyrightYear());
    }

}

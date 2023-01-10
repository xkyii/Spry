package com.xkyii.java.spry.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class LoginResource {

    @GET
    public String index() {
        return String.format("欢迎使用Spry");
    }

}

package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.entity.LoginQuery;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "ruoyi")
public interface RuoYiHttpClient {

    @POST
    @Path("login")
    public Response login(LoginQuery query);

    @POST
    @Path("logout")
    public Response logout();

    @GET
    @Path("getInfo")
    public Response getInfo();
}

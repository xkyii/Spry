package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "ruoyi")
@RegisterClientHeaders
public interface RuoYiHttpClient {

    @POST
    @Path("login")
    Response login(LoginCommand query);

    @POST
    @Path("logout")
    Response logout();

    @GET
    @Path("getInfo")
    Response getInfo();

    @GET
    @Path("captchaImage")
    Response captchaImage();

    @GET
    @Path("getRouters")
    Response getRouters();
}

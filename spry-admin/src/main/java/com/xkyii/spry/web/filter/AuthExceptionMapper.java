package com.xkyii.spry.web.filter;

import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.exception.AuthException;
import com.xkyii.spry.web.exception.LoginException;
import com.xkyii.spry.web.service.SysLoginInfoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.INTERNAL_SERVER_ERROR;

@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

    @Inject
    Logger logger;

    @Inject
    SysLoginInfoService loginInfoService;

    @Override
    public Response toResponse(AuthException e) {
        logger.warn("AuthException", e);

        SysLoginInfo info = loginInfoService.create(e.getUsername(), e.getCode(), e.getMessage());

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(AjaxResult.error(info.getMsg()))
            .build();
    }
}

package com.xkyii.spry.web.filter;

import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.exception.LoginException;
import com.xkyii.spry.web.service.SysLoginInfoService;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.xkyii.spry.web.constant.Constants.创建登录日志;
import static java.awt.SystemColor.info;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.INTERNAL_SERVER_ERROR;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<LoginException> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageService ems;

    @Inject
    SysLoginInfoService loginInfoService;

    @Override
    public Response toResponse(LoginException e) {
        logger.warn("LoginException", e);

        SysLoginInfo info = loginInfoService.create(e.getUsername(), e.getCode(), e.getMessage());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", INTERNAL_SERVER_ERROR);
        map.put("msg", info.getMsg());

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(map)
            .build();
    }
}

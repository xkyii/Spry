package com.xkyii.spry.web.service;

import com.xkyii.spry.common.constant.StatusType;
import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.repository.SysLoginInfoRepository;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import eu.bitwalker.useragentutils.UserAgent;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.runtime.util.StringUtil;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;

import java.util.Date;

import static com.xkyii.spry.web.constant.AdminError.登录成功;
import static com.xkyii.spry.web.constant.Constants.创建登录日志;

@ApplicationScoped
public class SysLoginInfoService {

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders headers;

    @Inject
    ErrorMessageService ems;

    @Inject
    Logger logger;

    @Inject
    Vertx vertx;

    @Inject
    SysLoginInfoRepository loginInfoRepository;

    public SysLoginInfo create(String username, int code) {
        return create(username, code, null);
    }

    public SysLoginInfo create(String username, int code, String extraMessage) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(headers.getHeaderString(HttpHeaders.USER_AGENT));

        SysLoginInfo info = new SysLoginInfo();
        info.setUserName(username);
        info.setIpaddr(uriInfo.getBaseUri().getHost()); // TODO: IpUtils
        info.setLoginLocation("内网IP"); // TODO: AddressUtils
        info.setBrowser(userAgent.getBrowser().getName());
        info.setOs(userAgent.getOperatingSystem().getName());
        info.setStatus(String.valueOf((code == 登录成功) ? StatusType.正常.ordinal() : StatusType.无效.ordinal()));
        info.setMsg(String.format("%s(%d)", ems.getMessage(code), code));
        info.setLoginTime(new Date());
        if (!StringUtil.isNullOrEmpty(extraMessage)) {
            info.setMsg(info.getMsg() + "-" + extraMessage);
        }

        vertx.eventBus().publish(创建登录日志, info);
        return info;
    }

    @ConsumeEvent(创建登录日志)
    @WithTransaction
    public Uni<SysLoginInfo> onCreate(SysLoginInfo info) {
        logger.info(创建登录日志);
        return loginInfoRepository.persist(info);
    }
}

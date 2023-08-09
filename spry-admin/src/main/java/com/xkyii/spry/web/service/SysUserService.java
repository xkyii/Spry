package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.constant.AdminError;
import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.repository.SysUserRepository;
import com.xkyss.quarkus.server.error.ServerException;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import eu.bitwalker.useragentutils.UserAgent;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;

import java.util.Date;

import static com.xkyii.spry.web.constant.Constants.创建登录日志;

@SuppressWarnings("NonAsciiCharacters")
@ApplicationScoped
public class SysUserService {

    @Inject
    Logger logger;

    @Inject
    TokenService tokenService;

    @Inject
    SysUserRepository userRepository;

    @Inject
    SysPermissionService permissionService;

    @Inject
    SysLoginInfoService loginInfoService;

    @Inject
    Vertx vertx;

    @WithTransaction
    public Uni<LoginOutput> login(LoginCommand input) {
        String username = input.getUsername();
        return userRepository.find("userName", username).firstResult()
            .onItem().ifNull().failWith(() -> {
                vertx.eventBus().publish(创建登录日志, genSysLoginInfo(username, AdminError.用户不存在));
                return new ServerException(AdminError.用户不存在, username);
            })
            // 校验密码
            .onItem().invoke(Unchecked.consumer(u -> {
                boolean matches = BcryptUtil.matches(input.getPassword(), u.getPassword());
                if (!matches) {
                    vertx.eventBus().publish(创建登录日志, genSysLoginInfo(username, AdminError.密码错误));
                    throw new ServerException(AdminError.密码错误);
                }
            }))
            // 创建登录日志
            .onItem().invoke(u -> vertx.eventBus().publish(创建登录日志, genSysLoginInfo(username, AdminError.登录成功)))
            // 生成token
            .onItem().transform(u -> new LoginOutput(tokenService.generateToken(u)))
            // 如果失败
            .onFailure().invoke(e -> vertx.eventBus().publish(创建登录日志, genSysLoginInfo(username, AdminError.登录失败)))
            ;
    }

    public Uni<AjaxResult> getInfo() {
        return Uni.createFrom().item(AjaxResult.success())
            .flatMap(r -> Uni.createFrom().item(r))
            ;
    }

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders headers;

    @Inject
    ErrorMessageService ems;

    private SysLoginInfo genSysLoginInfo(String username, Integer code) {

        final UserAgent userAgent = UserAgent.parseUserAgentString(headers.getHeaderString(HttpHeaders.USER_AGENT));

        SysLoginInfo info = new SysLoginInfo();
        info.setUserName(username);
        info.setIpaddr(uriInfo.getBaseUri().getHost()); // TODO: IpUtils
        info.setLoginLocation("内网IP"); // TODO: AddressUtils
        info.setBrowser(userAgent.getBrowser().getName());
        info.setOs(userAgent.getOperatingSystem().getName());
        info.setStatus(code.toString());
        info.setMsg(ems.getMessage(code));
        info.setLoginTime(new Date());

        return info;
    }
}

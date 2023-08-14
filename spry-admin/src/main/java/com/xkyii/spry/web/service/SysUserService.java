package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.constant.AdminError;
import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.exception.LoginException;
import com.xkyii.spry.web.model.LoginUser;
import com.xkyii.spry.web.repository.SysUserRepository;
import com.xkyss.quarkus.server.error.ServerException;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import org.jboss.logging.Logger;

import static com.xkyii.spry.web.constant.AdminError.*;
import static com.xkyii.spry.web.constant.Constants.ADMIN_CONTEXT_KEY_LOGIN_USER;

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

    @WithTransaction
    public Uni<LoginOutput> login(LoginCommand input) {
        String username = input.getUsername();
        return userRepository.find("userName", username).firstResult()
            .onItem().ifNull().failWith(new LoginException(username, 用户不存在))
            // 校验密码
            .onItem().invoke(Unchecked.consumer(u -> {
                boolean matches = BcryptUtil.matches(input.getPassword(), u.getPassword());
                if (!matches) {
                    throw new LoginException(username, 密码错误);
                }
            }))
            // 创建登录日志
            .onItem().invoke(u -> loginInfoService.create(username, 登录成功))
            // 转换为LoginUser
            .onItem().transformToUni(user -> permissionService.getRolePermission(user)
                .onItem().transform(permissions -> new LoginUser(user).withPermissions(permissions)))
            // 生成token
            .onItem().transform(loginUser -> new LoginOutput(tokenService.generateToken(loginUser)))
            // 如果失败
            .onFailure().transform(e -> {
                if (e instanceof LoginException) {
                    return e;
                }
                return new LoginException(username, 登录失败, e.getMessage());
            });
    }


    @Context
    SecurityIdentity securityIdentity;

    public Uni<AjaxResult> getInfo() {
        LoginUser loginUser = securityIdentity.getAttribute(ADMIN_CONTEXT_KEY_LOGIN_USER);
        SysUser sysUser = loginUser.getUser();

        return Uni.createFrom().item(AjaxResult.success())
            .onItem().transform(r -> r.put("user", sysUser))
            .onItem().transformToUni(r -> permissionService.getRolePermission(sysUser)
                .onItem().transform(roles -> r.put("roles", roles)))
            .onItem().transformToUni(r -> permissionService.getMenuPermission(sysUser)
                .onItem().transform(permissions -> r.put("permissions", permissions)))
            ;
    }
}

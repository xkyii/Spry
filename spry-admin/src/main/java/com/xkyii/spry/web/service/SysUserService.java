package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.exception.LoginException;
import com.xkyii.spry.web.model.LoginUser;
import com.xkyii.spry.web.repository.SysUserRepository;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.concurrent.CompletableFuture;

import static com.xkyii.spry.web.constant.AdminError.*;
import static com.xkyii.spry.web.constant.Constants.CACHE_NAME_LOGIN_USER;

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
            })
            ;
    }

    public Uni<AjaxResult> getInfo() {
        return Uni.createFrom().item(AjaxResult.success())
            .flatMap(r -> Uni.createFrom().item(r))
            ;
    }
}

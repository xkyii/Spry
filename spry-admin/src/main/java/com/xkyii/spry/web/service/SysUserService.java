package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.constant.AdminError;
import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.repository.SysUserRepository;
import com.xkyss.quarkus.server.error.ServerException;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

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
            .onItem().ifNull().failWith(new ServerException(AdminError.用户不存在, username))
            // 校验密码
            .onItem().invoke(Unchecked.consumer(u -> {
                boolean matches = BcryptUtil.matches(input.getPassword(), u.getPassword());
                if (!matches) {
                    throw new ServerException(AdminError.密码错误);
                }
            }))
            // 保存登录日志 (想要个异步效果)
            .onItem().invoke(u -> {
                vertx.eventBus().publish(创建登录日志, u);
            })
            // 生成token
            .onItem().transform(u -> new LoginOutput(tokenService.generateToken(u)))
            ;
    }

    public Uni<AjaxResult> getInfo() {
        return Uni.createFrom().item(AjaxResult.success())
            .flatMap(r -> Uni.createFrom().item(r))
            ;
    }

    @ConsumeEvent("greetings")
    @WithTransaction
    public Uni<SysLoginInfo> hello(SysUser user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String msg = "Hello " + user.getUserName();
        logger.infof("greetings: %s", msg);

        return loginInfoService.create(user);
    }
}

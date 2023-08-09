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
import io.smallrye.common.vertx.VertxContext;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.core.Vertx;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.EventLoopContext;
import io.vertx.core.impl.VertxInternal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import static io.quarkus.vertx.core.runtime.context.VertxContextSafetyToggle.setContextSafe;

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

    @Inject
    ScheduledExecutorService scheduledExecutorService;

    @Inject
    Executor executor;

    @Inject
    ExecutorService executorService;

    @Inject
    Mutiny.SessionFactory emf;

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

                // 运行在同一个线程,没报错,保存失败
                // Vertx.currentContext().runOnContext(e -> {
                //         loginInfoService.create(u)
                //             .subscribe().with(x -> {
                //                 logger.info("创建登录日志 in Vertx.currentContext()");
                //             }, Throwable::printStackTrace);
                // });

                // java.lang.IllegalStateException: HR000069: Detected use of the reactive Session from a different Thread than the one which was used to open the reactive Session - this suggests an invalid integration; original thread [245]: 'vert.x-eventloop-thread-3' current Thread [2456]: 'executor-thread-1'
                // executor.execute(() -> {
                //     loginInfoService.create(u)
                //         .subscribe().with(x -> {
                //             logger.info("创建登录日志 in executor");
                //         }, Throwable::printStackTrace);
                // });

                // java.lang.IllegalStateException: HR000069: Detected use of the reactive Session from a different Thread than the one which was used to open the reactive Session - this suggests an invalid integration; original thread [244]: 'vert.x-eventloop-thread-2' current Thread [2589]: 'executor-thread-1'
                // executorService.execute(() -> {
                //     loginInfoService.create(u)
                //         .subscribe().with(x -> {
                //             logger.info("创建登录日志 in executorService");
                //         }, Throwable::printStackTrace);
                // });

                // java.lang.IllegalStateException: HR000068: This method should exclusively be invoked from a Vert.x EventLoop thread; currently running on thread 'executor-thread-1'
                // scheduledExecutorService.schedule(() -> {
                //     loginInfoService.create(u)
                //         .subscribe().with(x -> {
                //             logger.info("创建登录日志");
                //         }, Throwable::printStackTrace);
                // }, 10, TimeUnit.MILLISECONDS);

                // 保存成功,但是实际上没有达到异步效果,只是subscribe在另一个线程而已
                // loginInfoService.create(u)
                //     .emitOn(Infrastructure.getDefaultWorkerPool())
                //     .subscribe().with(x -> {
                //         logger.info("创建登录日志 emitOn Infrastructure.getDefaultWorkerPool()");
                //     }, Throwable::printStackTrace);

                // java.lang.IllegalStateException: HR000069: Detected use of the reactive Session from a different Thread than the one which was used to open the reactive Session - this suggests an invalid integration; original thread [244]: 'vert.x-eventloop-thread-2' current Thread [1911]: 'executor-thread-1'
                // loginInfoService.create(u)
                //     .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                //     .subscribe().with(x -> {
                //         logger.info("创建登录日志 runSubscriptionOn Infrastructure.getDefaultWorkerPool()");
                //     }, Throwable::printStackTrace);

                // java.lang.IllegalStateException: HR000069: Detected use of the reactive Session from a different Thread than the one which was used to open the reactive Session - this suggests an invalid integration; original thread [245]: 'vert.x-eventloop-thread-3' current Thread [1056]: 'vert.x-worker-thread-2'
                // vertx.executeBlocking(e -> {
                //     logger.info("创建登录日志 vertx.executeBlocking");
                //     loginInfoService.create(u)
                //         .emitOn(Infrastructure.getDefaultWorkerPool())
                //         .subscribe().with(x -> {
                //             logger.info("创建登录日志 22");
                //         }, Throwable::printStackTrace);
                // });

                // ERROR [io.qua.ver.cor.run.VertxCoreRecorder] (vert.x-eventloop-thread-3) Uncaught exception received by Vert.x: java.lang.IllegalStateException: Can't get the context safety flag: the current context is not a duplicated context
                VertxInternal vxi = (VertxInternal) vertx;
                Executor delegate = vertx.nettyEventLoopGroup();
                EventLoopContext context = vxi.createEventLoopContext();
                ContextInternal internal = (ContextInternal) VertxContext
                    .getOrCreateDuplicatedContext(context);
                setContextSafe(internal, true);
                delegate.execute(() -> internal.dispatch(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    hello(u)
                        .subscribe().with(x -> {
                            logger.info("创建登录日志 22");
                        }, Throwable::printStackTrace);
                }));

                // vertx.eventBus().publish("greetings", u);
            })

            // .onItem().transformToUni(u -> {
            //     // 直接保存,成功,没有异步效果
            //     return loginInfoService.create(u).onItem().transform(i -> u);
            // })
            // 生成token
            .onItem().transform(u -> new LoginOutput(tokenService.generateToken(u)))
            ;
    }

    public Uni<AjaxResult> getInfo() {
        return Uni.createFrom().item(AjaxResult.success())
            .flatMap(r -> Uni.createFrom().item(r))
            ;
    }

    // @ConsumeEvent("greetings")
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

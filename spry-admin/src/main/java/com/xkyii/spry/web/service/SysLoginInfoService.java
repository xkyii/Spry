package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.repository.SysLoginInfoRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;

import java.util.Date;

import static com.xkyii.spry.web.constant.Constants.创建登录日志;

@ApplicationScoped
public class SysLoginInfoService {

    @Inject
    Logger logger;

    @Inject
    SysLoginInfoRepository loginInfoRepository;

    @ConsumeEvent(创建登录日志)
    @WithTransaction
    public Uni<SysLoginInfo> create(SysLoginInfo info) {
        logger.info(创建登录日志);
        return loginInfoRepository.persist(info);
    }
}

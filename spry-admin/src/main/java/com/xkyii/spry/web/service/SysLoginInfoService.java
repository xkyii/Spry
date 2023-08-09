package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.repository.SysLoginInfoRepository;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Date;

@ApplicationScoped
public class SysLoginInfoService {

    @Inject
    Logger logger;

    @Inject
    SysLoginInfoRepository loginInfoRepository;


    // @WithTransaction
    public Uni<SysLoginInfo> create(SysUser user) {

        SysLoginInfo info = new SysLoginInfo();
        // info.setInfoId(180L);
        info.setUserName(user.getUserName());
        info.setIpaddr("127.0.0.1");
        info.setLoginLocation("内网IP");
        info.setBrowser("Chrome 11");
        info.setOs("Unknown");
        info.setStatus("1");
        info.setMsg("成功");
        info.setLoginTime(new Date());


        logger.info("创建登录日志");

        // return Panache.withTransaction(() -> {
            return loginInfoRepository.persist(info);
        // });
    }
}

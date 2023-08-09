package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysLoginInfo;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysLoginInfoRepository implements PanacheRepository<SysLoginInfo> {
}

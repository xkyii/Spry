
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysLoginInfo;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysLoginInfoRepository implements PanacheRepository<SysLoginInfo> {
}

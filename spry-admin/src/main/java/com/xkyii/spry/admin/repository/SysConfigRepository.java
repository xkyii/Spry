
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysConfig;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysConfigRepository implements PanacheRepository<SysConfig> {
}

package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysUser;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysUserRepository implements PanacheRepository<SysUser> {
}

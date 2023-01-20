package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysUser;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysUserRepository implements PanacheRepository<SysUser> {
}
